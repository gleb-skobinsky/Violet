package com.violet.features.notes.models

import kotlinx.serialization.Serializable

@Serializable
data class NoteResponse(
    val title: String,
    val body: String
)
