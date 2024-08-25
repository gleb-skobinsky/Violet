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
            val envMap = File(".env").readText()
                .split('\n')
                .associate { it.split('=').run { first() to last() } }
            return AppSecrets(
                smtpServerHost = envMap.getValue("smtp_server_host"),
                smtpServerPort = envMap.getValue("smtp_server_port").toInt(),
                smtpServerUserName = envMap.getValue("smtp_server_user_name"),
                smtpServerPassword = envMap.getValue("smtp_server_password"),
                emailFrom = envMap.getValue("email_from"),
                dbPort = envMap.getValue("db_port").toInt(),
                dbName = envMap.getValue("db_name"),
                dbUser = envMap.getValue("db_user"),
                dbPassword = envMap.getValue("db_password")
            )
        }
    }
}