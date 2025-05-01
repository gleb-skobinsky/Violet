package com.violet.features.notes.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateNoteRequest(
    val title: String,
    val body: String
)
