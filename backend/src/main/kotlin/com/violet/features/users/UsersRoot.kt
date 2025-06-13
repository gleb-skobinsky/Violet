package com.violet.features.users

import com.violet.features.users.models.UserData
import com.violet.features.users.models.toUpdatedUser
import com.violet.features.users.repository.UsersRepository
import com.violet.jwt.JWTConfig.Companion.JWT_AUTH_ID
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureUsersRoutes(
    usersRepository: UsersRepository
) {
    routing {
        // Read user
        authenticate(JWT_AUTH_ID) {
            route("/users/{id}") {
                usersEndpointDescription()
                get {
                    val id = call.parameters["id"] ?: run {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "Invalid id"
                        )
                        return@get
                    }
                    val user = usersRepository.readById(id)
                    if (user != null) {
                        call.respond(HttpStatusCode.OK, user)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
                put {
                    val id = call.parameters["id"] ?: run {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "Invalid id"
                        )
                        return@put
                    }
                    val user = call.receive<UserData>()
                    usersRepository.update(id, user.toUpdatedUser())
                    call.respond(HttpStatusCode.OK)
                }
                delete {
                    val id = call.parameters["id"] ?: run {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "Invalid id"
                        )
                        return@delete
                    }
                    usersRepository.delete(id)
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }
}


