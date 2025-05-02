package com.violet.features.notes.routes

import com.violet.features.notes.models.CreateNoteRequest
import com.violet.features.notes.models.NoteResponse
import com.violet.features.notes.repository.NotesRepository
import com.violet.jwt.JWTConfig
import com.violet.jwt.email
import com.violet.shared.RepositoriesTags
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Routing.createNoteRoute(
    repository: NotesRepository,
) {
    authenticate(JWTConfig.JWT_AUTH_ID) {
        route("/api/notes/create") {
            install(NotarizedRoute()) {
                tags = setOf(RepositoriesTags.NOTES)
                post = PostInfo.builder {
                    summary("Create a note")
                    description("The endpoint for note creation given a title and a body")
                    request {
                        description("Create a note")
                        requestType<CreateNoteRequest>()
                    }
                    response {
                        description("Note successfully created")
                        responseCode(HttpStatusCode.Created)
                        responseType<Boolean>()
                    }
                }
            }
            post {
                val email = call.principal<JWTPrincipal>()?.email ?: run {
                    call.respond(
                        HttpStatusCode.Unauthorized,
                        "Request authentication failed"
                    )
                    return@post
                }
                val note = call.receive<CreateNoteRequest>()
                val created = repository.saveNote(
                    userEmail = email,
                    title = note.title,
                    body = note.body
                )
                if (created) {
                    call.respond(
                        HttpStatusCode.Created,
                        true
                    )
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}