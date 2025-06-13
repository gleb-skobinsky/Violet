package org.violet.violetapp.auth.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    val id: String,
    val email: String? = null,
    val role: String? = null
)
