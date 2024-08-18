package com.violet.email.data

import com.violet.email.data.AppSecrets.EMAIL_FROM

data class EmailData(
    val emailFrom: String,
    val emailTo: String,
    val subject: String,
    val message: String
) {
    companion object {
        fun withDefaultSender(
            emailTo: String,
            subject: String,
            message: String
        ) = EmailData(
            emailFrom = EMAIL_FROM,
            emailTo = emailTo,
            subject = subject,
            message = message
        )
    }
}
