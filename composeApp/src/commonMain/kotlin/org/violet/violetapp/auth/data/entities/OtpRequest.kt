package org.violet.violetapp.auth.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class OtpRequest(
    val login: String
)