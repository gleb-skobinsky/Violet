package com.violet.features.users

import com.violet.jwt.JWTConfig.Companion.JWT_AUTH_ID
import com.violet.users.data.ExposedUser
import com.violet.users.data.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUsersRoutes(
    userService: UserService
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
                    val user = userService.readById(id)
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
                    userService.update(id, user)
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
                    userService.delete(id)
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }
}


