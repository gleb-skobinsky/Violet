package com.violet.plugins

import io.bkbn.kompendium.core.metadata.*
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )
    val userService = UserService(database)
    routing {
        // Create user
        route("/users") {
            install(NotarizedRoute()) {
                post = PostInfo.builder {
                    summary("Create a user")
                    description("User creation endpooint")
                    request {
                        description("Create a user")
                        requestType<ExposedUser>()
                    }
                    response {
                        description("User successfully created")
                        responseCode(HttpStatusCode.Created)
                        responseType<String>()
                    }
                }
            }
            post {
                val user = call.receive<ExposedUser>()
                val id = userService.create(user)
                call.respond(HttpStatusCode.Created, id)
            }
        }

        // Read user
        route("/users/{id}") {
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
            get {
                val id = call.parameters["id"]?.toIntOrNull() ?: run {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Invalid id"
                    )
                    return@get
                }
                val user = userService.read(id)
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

