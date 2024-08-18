package com.violet.email

import com.violet.email.data.AppSecrets
import com.violet.email.data.DefaultEmailService
import com.violet.email.data.EmailService
import org.koin.dsl.module
import org.simplejavamail.api.mailer.Mailer
import org.simplejavamail.api.mailer.config.TransportStrategy
import org.simplejavamail.mailer.MailerBuilder

val emailKoinModule = module {
    single<Mailer> {
        MailerBuilder
            .withSMTPServer(AppSecrets.SMTP_SERVER_HOST, AppSecrets.SMTP_SERVER_PORT)
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .withSMTPServerUsername(AppSecrets.SMTP_SERVER_USER_NAME)
            .withSMTPServerPassword(AppSecrets.SMTP_SERVER_PASSWORD)
            .buildMailer()

    }
    single<EmailService> { DefaultEmailService(get()) }
}