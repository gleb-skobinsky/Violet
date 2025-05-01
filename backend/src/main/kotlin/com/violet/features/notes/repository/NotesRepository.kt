package com.violet.features.notes.repository

import com.violet.features.notes.models.NoteResponse

interface NotesRepository {
    suspend fun getNotesForUser(email: String): List<NoteResponse>

    suspend fun saveNote(
        userEmail: String,
        title: String,
        body: String
    ): Boolean

    suspend fun deleteNote(noteId: Int): Boolean
}