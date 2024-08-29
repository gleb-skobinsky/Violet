package org.violet.violetapp.common.network

import kotlinx.serialization.Serializable

@Serializable
data class ServerResponse(
    val error: Boolean,
    val message: String? = null
)