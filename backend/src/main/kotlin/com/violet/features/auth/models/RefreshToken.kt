package com.violet.features.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(val token: String)