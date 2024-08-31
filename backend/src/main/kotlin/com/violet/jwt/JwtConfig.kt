package com.violet.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import java.util.Date
import kotlin.time.Duration

data class JWTConfig(
    val realm: String,
    val secret: String,
    val audience: String,
    val issuer: String
)

fun JWTConfig.createToken(
    email: String,
    type: TokenType,
    expiration: Duration
): String =
    JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withJWTId(type.name)
        .withClaim("email", email)
        .withExpiresAt(Date(System.currentTimeMillis() + expiration.inWholeMilliseconds))
        .sign(Algorithm.HMAC256(secret))

fun JWTConfig.verifyToken(token: String, type: TokenType): String? =
    try {
        val jwt = JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withJWTId(type.name)
            .withIssuer(issuer)
            .build()
            .verify(token)
        jwt.getClaim("email").asString()
    } catch (e: JWTVerificationException) {
        null
    }