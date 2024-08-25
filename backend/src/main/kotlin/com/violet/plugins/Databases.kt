package com.violet.plugins

import com.violet.users.data.ExposedUser
import com.violet.users.data.UserService
import io.bkbn.kompendium.core.metadata.DeleteInfo
import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.metadata.PutInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureDatabases(
    userService: UserService
) {
    routing {
        // Read user
        route("/users/{id}") {
            installGetUserDescription()
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

private fun Route.installGetUserDescription() {
    install(NotarizedRoute()) {
        get = GetInfo.builder {
            summary("Get a user")
            description("User retrieval endpoint")
            parameters(
                Parameter(
                    name = "id",
                    `in` = Parameter.Location.path,
                    schema = TypeDefinition.INT
                )
            )
            response {
                description("User successfully retrieved")
                responseCode(HttpStatusCode.OK)
                responseType<ExposedUser>()
            }
            canRespond {
                description("User not found")
                responseCode(HttpStatusCode.NotFound)
                responseType<Unit>()
            }
            canRespond {
                description("User request is inavlid")
                responseCode(HttpStatusCode.BadRequest)
                responseType<Unit>()
            }
        }
        put = PutInfo.builder {
            summary("Update a user")
            description("User update endpoint")
            parameters(
                Parameter(
                    name = "id",
                    `in` = Parameter.Location.path,
                    schema = TypeDefinition.INT
                )
            )
            request {
                description("Update a user")
                requestType<ExposedUser>()
            }
            response {
                description("User successfully updated")
                responseCode(HttpStatusCode.OK)
                responseType<Unit>()
            }
            canRespond {
                description("User update request is invalid")
                responseCode(HttpStatusCode.BadRequest)
                responseType<Unit>()
            }
        }
        delete = DeleteInfo.builder {
            summary("Delete a user")
            description("User deletion endpoint")
            parameters(
                Parameter(
                    name = "id",
                    `in` = Parameter.Location.path,
                    schema = TypeDefinition.INT
                )
            )
            response {
                description("User successfully deleted")
                responseCode(HttpStatusCode.OK)
                responseType<Unit>()
            }
            canRespond {
                description("User deletion request is invalid")
                responseCode(HttpStatusCode.BadRequest)
                responseType<Unit>()
            }
        }
    }
}

