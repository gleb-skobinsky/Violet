package com.violet.features.notes.routes

import com.violet.features.notes.repository.NotesRepository
import com.violet.jwt.JWTConfig
import com.violet.shared.RepositoriesTags
import io.bkbn.kompendium.core.metadata.DeleteInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.payload.Parameter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val NOTE_ID_PARAM = "noteId"

internal fun Routing.deleteNoteRoute(
    repository: NotesRepository,
) {
    authenticate(JWTConfig.JWT_AUTH_ID) {
        route("/api/notes/{$NOTE_ID_PARAM}") {
            install(NotarizedRoute()) {
                tags = setOf(RepositoriesTags.NOTES)
                delete = DeleteInfo.builder {
                    summary("Delete a note")
                    description("The endpoint for note deletion given its id")
                    parameters(
                        Parameter(
                            name = NOTE_ID_PARAM,
                            `in` = Parameter.Location.path,
                            schema = TypeDefinition.INT
                        )
                    )
                    response {
                        description("Note successfully deleted")
                        responseCode(HttpStatusCode.OK)
                        responseType<Boolean>()
                    }
                }
            }
            delete {
                val id = call.parameters[NOTE_ID_PARAM]?.toIntOrNull() ?: run {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Invalid id"
                    )
                    return@delete
                }
                val deleted = repository.deleteNote(id)
                if (deleted) {
                    call.respond(
                        HttpStatusCode.OK,
                        true
                    )
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}