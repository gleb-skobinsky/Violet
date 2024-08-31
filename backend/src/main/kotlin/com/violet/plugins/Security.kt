package com.violet.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.violet.email.data.AppSecrets
import com.violet.email.data.EmailData
import com.violet.email.data.EmailService
import com.violet.users.data.ExposedUser
import com.violet.users.data.SimpleUserRequest
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
import java.util.Date
import java.util.Random

const val JWT_AUTH_ID = "matchme-jwt-auth"

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
    authentication {
        jwt(JWT_AUTH_ID) {
            realm = secrets.jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secrets.jwtSecret))
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
            get("login") {
                call.respondRedirect("/callback")
            }

            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
                call.sessions.set(UserSession(principal?.accessToken.toString()))
                call.respondRedirect("/hello")
            }
        }
        route("/direct-login") {
            install(NotarizedRoute()) {
                tags = setOf("Auth Repository")
                post = PostInfo.builder {
                    summary("Login")
                    description("Login a user with his username and password")
                    request {
                        description("Login a user")
                        requestType<SimpleUserRequest>()
                    }
                    response {
                        description("User successfully logged in")
                        responseCode(HttpStatusCode.Created)
                        responseType<TokenData>()
                    }
                }
            }
            post {
                val user = call.receive<SimpleUserRequest>()
                val dbUser = userService.readByEmail(user.email) ?: run {
                    call.respond(HttpStatusCode.NotFound, "User name not found")
                    return@post
                }
                if (user.password != dbUser.password) {
                    call.respond(HttpStatusCode.Unauthorized, "Password is incorrect")
                    return@post
                }
                val token = JWT.create()
                    .withAudience(secrets.jwtAudience)
                    .withIssuer(secrets.jwtIssuer)
                    .withClaim("name", user.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + 60000))
                    .sign(Algorithm.HMAC256(secrets.jwtSecret))
                call.respond(TokenData(token))
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
                        requestType<SimpleUserRequest>()
                    }
                    response {
                        description("User successfully registered")
                        responseCode(HttpStatusCode.Created)
                        responseType<String>()
                    }
                }
            }
            post {
                val user = call.receive<SimpleUserRequest>()
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
private data class TokenData(val token: String)

data class UserSession(val accessToken: String)
