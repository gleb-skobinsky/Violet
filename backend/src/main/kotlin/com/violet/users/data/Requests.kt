package com.violet.users.data

import kotlinx.serialization.Serializable

@Serializable
data class ExposedUser(val email: String, val password: String, val verified: Boolean)

@Serializable
data class SimpleUser(val email: String, val password: String)

@Serializable
data class RefreshToken(val token: String)