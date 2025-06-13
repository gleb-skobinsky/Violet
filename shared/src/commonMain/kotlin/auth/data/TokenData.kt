package auth.data

import kotlinx.serialization.Serializable

@Serializable
data class TokenData(
    val accessToken: String,
    val refreshToken: String
)