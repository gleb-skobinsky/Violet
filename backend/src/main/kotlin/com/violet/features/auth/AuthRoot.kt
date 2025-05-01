package com.violet.features.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.violet.email.data.AppSecrets
import com.violet.email.data.EmailService
import com.violet.features.auth.models.UserSession
import com.violet.features.auth.routes.loginRoute
import com.violet.features.auth.routes.refreshTokenRoute
import com.violet.features.auth.routes.signupRoute
import com.violet.jwt.JWTConfig
import com.violet.users.data.UserService
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes

const val JWT_AUTH_ID = "matchme-jwt-auth"
val ACCESS_EXPIRATION_TIMEOUT = 30.minutes
val REFRESH_EXPIRATION_TIMEOUT = 7.days

private fun AppSecrets.toJwtConfig(): JWTConfig = JWTConfig(
    realm = jwtRealm,
    secret = jwtSecret,
    audience = jwtAudience,
    issuer = jwtIssuer
)

fun Application.configureAuth(
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
        loginRoute(userService, jwtConfig)
        refreshTokenRoute(jwtConfig)
        signupRoute(userService, secrets, emailService)
    }
}

