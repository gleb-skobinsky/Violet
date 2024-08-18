package com.violet.email.data

import org.simplejavamail.api.mailer.Mailer
import org.simplejavamail.email.EmailBuilder

class DefaultEmailService(
    private val mailer: Mailer
) : EmailService {
    override suspend fun sendEmail(data: EmailData): Boolean {
        val userName = data.emailTo.split("@").getOrNull(0) ?: return false
        val email = EmailBuilder.startingBlank()
            .from("e.g username, verify, etc.", data.emailFrom)
            .to(userName, data.emailTo)
            .withSubject(data.subject)
            .withPlainText(data.message)
            .buildEmail()
        return try {
            mailer.sendMail(email)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}