package com.violet.email

import com.violet.email.data.AppSecrets
import com.violet.email.data.DefaultEmailService
import com.violet.email.data.EmailService
import org.koin.dsl.module
import org.simplejavamail.api.mailer.Mailer
import org.simplejavamail.api.mailer.config.TransportStrategy
import org.simplejavamail.mailer.MailerBuilder

fun emailKoinModule(secrets: AppSecrets) = module {
    single<Mailer> {
        MailerBuilder
            .withSMTPServer(secrets.smtpServerHost, secrets.smtpServerPort)
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .withSMTPServerUsername(secrets.smtpServerUserName)
            .withSMTPServerPassword(secrets.smtpServerPassword)
            .buildMailer()

    }
    single<EmailService> { DefaultEmailService(get()) }
}