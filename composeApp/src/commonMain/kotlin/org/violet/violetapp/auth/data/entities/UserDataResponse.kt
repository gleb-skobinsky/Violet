package org.violet.violetapp.auth.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    val error: String? = null,
    val id: Int,
    @SerialName("merchant_id")
    val merchantId: Int,
    val phone: String? = null,
    val role: String? = null
)
