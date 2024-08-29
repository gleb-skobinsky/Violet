package org.violet.violetapp.auth.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CheckOtpResponse(
    val error: Boolean,
    val message: String? = null,
    val token: String? = null
)