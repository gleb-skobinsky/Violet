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
    val dbPassword: String
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
                smtpServerHost = envMap.getEnvString("smtp_server_host"),
                smtpServerPort = envMap.getEnvInt("smtp_server_port"),
                smtpServerUserName = envMap.getEnvString("smtp_server_user_name"),
                smtpServerPassword = envMap.getEnvString("smtp_server_password"),
                emailFrom = envMap.getEnvString("email_from"),
                dbPort = envMap.getEnvInt("db_port"),
                dbName = envMap.getEnvString("db_name"),
                dbUser = envMap.getEnvString("db_user"),
                dbPassword = envMap.getEnvString("db_password")
            )
        }

        private fun Map<String, String>.getEnvString(key: String): String = System.getenv(key) ?: getValue(key)

        private fun Map<String, String>.getEnvInt(key: String): Int = (System.getenv(key) ?: getValue(key)).toInt()
    }
}