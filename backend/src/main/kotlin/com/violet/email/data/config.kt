package com.violet.email.data

import java.io.File


data class AppSecrets(
    val smtpServerHost: String,
    val smtpServerPort: Int,
    val smtpServerUserName: String,
    val smtpServerPassword: String,
    val emailFrom: String,
    val dbPort: Int,
    val dbName: String,
    val dbUser: String,
    val dbPassword: String,
    val jwtAudience: String,
    val jwtIssuer: String,
    val jwtRealm: String,
    val jwtSecret: String
) {
    companion object {
        fun fromEnvironment(): AppSecrets {
            val envFile = File(".env")
            val envMap = if (envFile.exists()) {
                envFile.readText()
                    .split('\n')
                    .associate { it.split('=').run { first() to last() } }
            } else emptyMap()
            return AppSecrets(
                smtpServerHost = envMap.getEnvString("SMTP_SERVER_HOST"),
                smtpServerPort = envMap.getEnvInt("SMTP_SERVER_PORT"),
                smtpServerUserName = envMap.getEnvString("SMTP_SERVER_USER_NAME"),
                smtpServerPassword = envMap.getEnvString("SMTP_SERVER_PASSWORD"),
                emailFrom = envMap.getEnvString("EMAIL_FROM"),
                dbPort = envMap.getEnvInt("DATABASE_PORT"),
                dbName = envMap.getEnvString("DATABASE_NAME"),
                dbUser = envMap.getEnvString("DATABASE_USER"),
                dbPassword = envMap.getEnvString("DATABASE_PASSWORD"),
                jwtAudience = envMap.getEnvString("JWT_AUDIENCE"),
                jwtIssuer = envMap.getEnvString("JWT_ISSUER"),
                jwtRealm = envMap.getEnvString("JWT_REALM"),
                jwtSecret = envMap.getEnvString("JWT_SECRET")
            )
        }

        private fun Map<String, String>.getEnvString(key: String): String = System.getenv(key) ?: getValue(key)

        private fun Map<String, String>.getEnvInt(key: String): Int = (System.getenv(key) ?: getValue(key)).toInt()
    }
}