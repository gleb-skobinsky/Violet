package com.violet.features.users

import com.violet.features.users.models.ExposedUser
import com.violet.features.users.repository.UsersRepository
import com.violet.jwt.JWTConfig.Companion.JWT_AUTH_ID
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUsersRoutes(
    usersRepository: UsersRepository
) {
    routing {
        // Read user
        authenticate(JWT_AUTH_ID) {
            route("/users/{id}") {
                usersEndpointDescription()
                get {
                    val id = call.parameters["id"]?.toIntOrNull() ?: run {
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
                    val id = call.parameters["id"]?.toIntOrNull() ?: run {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "Invalid id"
                        )
                        return@put
                    }
                    val user = call.receive<ExposedUser>()
                    usersRepository.update(id, user)
                    call.respond(HttpStatusCode.OK)
                }
                delete {
                    val id = call.parameters["id"]?.toIntOrNull() ?: run {
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


