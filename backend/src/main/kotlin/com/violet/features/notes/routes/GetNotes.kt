package com.violet.features.notes.routes

import com.violet.features.notes.models.NoteResponse
import com.violet.features.notes.repository.NotesRepository
import com.violet.jwt.JWTConfig
import com.violet.jwt.email
import com.violet.shared.RepositoriesTags
import io.bkbn.kompendium.core.metadata.GetInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Routing.getNotesRoute(
    repository: NotesRepository,
) {
    authenticate(JWTConfig.JWT_AUTH_ID) {
        route("/api/notes") {
            install(NotarizedRoute()) {
                tags = setOf(RepositoriesTags.NOTES)
                get = GetInfo.builder {
                    summary("Notes for user")
                    description("All notes for the given user")
                    response {
                        description("Notes list successfully retrieved")
                        responseCode(HttpStatusCode.OK)
                        responseType<List<NoteResponse>>()
                    }
                }
            }
            get {
                val email = call.principal<JWTPrincipal>()?.email ?: run {
                    call.respond(
                        HttpStatusCode.Unauthorized,
                        "Request authentication failed"
                    )
                    return@get
                }
                val notes = repository.getNotesForUser(email)
                call.respond(
                    status = HttpStatusCode.OK,
                    message = notes
                )
            }
        }
    }
}