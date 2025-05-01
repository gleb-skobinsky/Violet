package com.violet.features.users.models

import kotlinx.serialization.Serializable

@Serializable
data class ExposedUser(
    val email: String,
    val password: String,
    val verified: Boolean
)