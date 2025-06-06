package com.violet.features.notes

import com.violet.features.notes.repository.NotesRepository
import com.violet.features.notes.routes.createNoteRoute
import com.violet.features.notes.routes.deleteNoteRoute
import com.violet.features.notes.routes.getNotesRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureNotes(
    repository: NotesRepository
) {
    routing {
        getNotesRoute(repository)
        createNoteRoute(repository)
        deleteNoteRoute(repository)
    }
}