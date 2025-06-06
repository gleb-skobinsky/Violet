package com.violet.features.auth.routes

import com.violet.features.auth.models.TokenData
import com.violet.features.users.models.SimpleUser
import com.violet.features.users.repository.UsersRepository
import com.violet.jwt.JWTConfig
import com.violet.jwt.JWTConfig.Companion.ACCESS_EXPIRATION_TIMEOUT
import com.violet.jwt.JWTConfig.Companion.REFRESH_EXPIRATION_TIMEOUT
import com.violet.jwt.TokenType
import com.violet.jwt.createToken
import com.violet.shared.RepositoriesTags
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Routing.loginRoute(
    usersRepository: UsersRepository,
    jwtConfig: JWTConfig
) {
    route("/login") {
        install(NotarizedRoute()) {
            tags = setOf(RepositoriesTags.AUTH)
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
            val dbUser = usersRepository.readByEmail(user.email) ?: run {
                call.respond(HttpStatusCode.NotFound, "User name not found")
                return@post
            }
            if (user.password != dbUser.password) {
                call.respond(HttpStatusCode.Unauthorized, "Password is incorrect")
                return@post
            }
            val accessToken = jwtConfig.createToken(
                user.email,
                TokenType.AccessToken,
                ACCESS_EXPIRATION_TIMEOUT
            )
            val refreshToken = jwtConfig.createToken(
                user.email,
                TokenType.RefreshToken,
                REFRESH_EXPIRATION_TIMEOUT
            )
            call.respond(TokenData(accessToken, refreshToken))
        }
    }
}
