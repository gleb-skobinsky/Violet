package com.violet.features.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class SignupResponse(
    val emailSent: Boolean,
    val message: String
)
