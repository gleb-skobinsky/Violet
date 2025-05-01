package com.violet.features.notes.repository

import com.violet.features.notes.models.NoteResponse

interface NotesRepository {
    suspend fun getNotesForUser(email: String): List<NoteResponse>

    suspend fun saveNote(title: String, body: String): Boolean
}