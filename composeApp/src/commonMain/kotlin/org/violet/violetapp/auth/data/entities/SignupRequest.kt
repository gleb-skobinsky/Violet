package org.violet.violetapp.auth.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    val login: String,
    val password: String
)