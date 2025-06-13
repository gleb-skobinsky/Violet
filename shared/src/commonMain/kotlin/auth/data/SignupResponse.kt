package auth.data

import kotlinx.serialization.Serializable

@Serializable
data class SignupResponse(
    val emailSent: Boolean,
    val message: String
)