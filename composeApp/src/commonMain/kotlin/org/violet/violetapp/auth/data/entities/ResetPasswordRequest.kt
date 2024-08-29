package org.violet.violetapp.auth.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequest(
    val login: String,
    val password: String,
    val otpToken: String
)