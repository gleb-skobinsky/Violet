package com.violet.email.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppSecrets(
    @SerialName("smtp_server_host")
    val smtpServerHost: String,
    @SerialName("smtp_server_port")
    val smtpServerPort: Int,
    @SerialName("smtp_server_user_name")
    val smtpServerUserName: String,
    @SerialName("smtp_server_password")
    val smtpServerPassword: String,
    @SerialName("email_from")
    val emailFrom: String,
    @SerialName("db_port")
    val dbPort: Int,
    @SerialName("db_name")
    val dbName: String,
    @SerialName("db_user")
    val dbUser: String,
    @SerialName("db_password")
    val dbPassword: String
)