package com.violet.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.violet.email.data.AppSecrets
import com.violet.email.data.EmailData
import com.violet.email.data.EmailService
import com.violet.jwt.JWTConfig
import com.violet.jwt.createToken
import com.violet.jwt.verify
import com.violet.users.data.ExposedUser
import com.violet.users.data.RefreshToken
import com.violet.users.data.SimpleUser
import com.violet.users.data.UserService
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.auth.OAuthAccessTokenResponse
import io.ktor.server.auth.OAuthServerSettings
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.auth.oauth
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import kotlinx.serialization.Serializable
import java.util.Random
import kotlin.time.Duration.Companion.minutes

const val JWT_AUTH_ID = "matchme-jwt-auth"
private val ACCESS_EXPIRATION_TIMEOUT = 1.minutes
private val REFRESH_EXPIRATION_TIMEOUT = 7.minutes

private fun AppSecrets.toJwtConfig(): JWTConfig = JWTConfig(
    realm = jwtRealm,
    secret = jwtSecret,
    audience = jwtAudience,
    issuer = jwtIssuer
)

fun Application.configureSecurity(
    secrets: AppSecrets,
    userService: UserService,
    emailService: EmailService
) {
    authentication {
        oauth("auth-oauth-google") {
            urlProvider = { "http://localhost:8080/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("GOOGLE_CLIENT_ID"),
                    clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile")
                )
            }
            client = HttpClient(Apache)
        }
    }
    val jwtConfig = secrets.toJwtConfig()
    authentication {
        jwt(JWT_AUTH_ID) {
            realm = secrets.jwtRealm
            verifier(
                JWT.require(Algorithm.HMAC256(secrets.jwtSecret))
                    .withAudience(secrets.jwtAudience)
                    .withIssuer(secrets.jwtIssuer)
                    .build()
            )
            validate { credential ->
                if (secrets.jwtAudience in credential.payload.audience) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }
    routing {
        authenticate("auth-oauth-google") {
            get("/login") {
                call.respondRedirect("/callback")
            }

            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
                call.sessions.set(UserSession(principal?.accessToken.toString()))
                call.respondRedirect("/hello")
            }
        }
        route("/login") {
            install(NotarizedRoute()) {
                tags = setOf("Auth Repository")
                post = PostInfo.builder {
                    summary("Login")
                    description("Login a user with his username and password")
                    request {
                        description("Login a user")
                        requestType<SimpleUser>()
                    }
                    response {
                        description("User successfully logged in")
                        responseCode(HttpStatusCode.Created)
                        responseType<TokenData>()
                    }
                }
            }
            post {
                val user = call.receive<SimpleUser>()
                val dbUser = userService.readByEmail(user.email) ?: run {
                    call.respond(HttpStatusCode.NotFound, "User name not found")
                    return@post
                }
                if (user.password != dbUser.password) {
                    call.respond(HttpStatusCode.Unauthorized, "Password is incorrect")
                    return@post
                }
                val accessToken = jwtConfig.createToken(user.email, ACCESS_EXPIRATION_TIMEOUT)
                val refreshToken = jwtConfig.createToken(user.email, REFRESH_EXPIRATION_TIMEOUT)
                call.respond(TokenData(accessToken, refreshToken))
            }
        }
        route("/refresh") {
            install(NotarizedRoute()) {
                tags = setOf("Auth Repository")
                post = PostInfo.builder {
                    summary("Refresh")
                    description("Refresh JWT token")
                    request {
                        description("Refresh a token")
                        requestType<RefreshToken>()
                    }
                    response {
                        description("Token successfully refreshed")
                        responseCode(HttpStatusCode.OK)
                        responseType<TokenData>()
                    }
                }
            }
            post {
                // Extract the refresh token from the request
                val refreshToken = call.receive<RefreshToken>()

                // Verify the refresh token and obtain the user
                val email = jwtConfig.verify(refreshToken.token) ?: run {
                    call.respond(HttpStatusCode.Forbidden, "Invalid refresh token")
                    return@post
                }

                // Create new access and refresh tokens for the user
                val newAccessToken = jwtConfig.createToken(email, ACCESS_EXPIRATION_TIMEOUT)
                val newRefreshToken = jwtConfig.createToken(email, REFRESH_EXPIRATION_TIMEOUT)

                // Respond with the new tokens
                call.respond(TokenData(newAccessToken, newRefreshToken))
            }
        }
        route("/signup") {
            install(NotarizedRoute()) {
                tags = setOf("Auth Repository")
                post = PostInfo.builder {
                    summary("User signup")
                    description("Sign up a user with their email and password")
                    request {
                        description("Register a user")
                        requestType<SimpleUser>()
                    }
                    response {
                        description("User successfully registered")
                        responseCode(HttpStatusCode.Created)
                        responseType<String>()
                    }
                }
            }
            post {
                val user = call.receive<SimpleUser>()
                userService.readByEmail(user.email)?.let {
                    call.respond(HttpStatusCode.BadRequest, "User already exists")
                    return@post
                }
                userService.create(
                    ExposedUser(email = user.email, password = user.password, verified = false)
                )
                val emailData = EmailData(
                    emailFrom = secrets.emailFrom,
                    emailTo = user.email,
                    message = "Verify your email using the following code: ${Random().nextInt(999999)}",
                    subject = "Email verification"
                )
                emailService.sendEmail2(emailData)
                call.respond(HttpStatusCode.OK, "Check your inbox for a verification email")
            }
        }
    }
}

@Serializable
private data class TokenData(val accessToken: String, val refreshToken: String)

data class UserSession(val accessToken: String)
