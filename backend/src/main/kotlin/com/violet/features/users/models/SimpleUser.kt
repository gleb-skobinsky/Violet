package com.violet.features.users.models

import kotlinx.serialization.Serializable

@Serializable
data class SimpleUser(
    val email: String,
    val password: String
)