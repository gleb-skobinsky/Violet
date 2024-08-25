package com.violet.email

import com.violet.email.data.AppSecrets
import com.violet.email.data.DefaultEmailService
import com.violet.email.data.EmailService
import org.koin.core.module.Module
import org.koin.dsl.module
import org.simplejavamail.api.mailer.Mailer
import org.simplejavamail.api.mailer.config.TransportStrategy
import org.simplejavamail.mailer.MailerBuilder

val emailKoinModule: Module = module {
    single<Mailer> {
        val secrets: AppSecrets = get()
        MailerBuilder
            .withSMTPServer(secrets.smtpServerHost, secrets.smtpServerPort)
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
            .withSMTPServerUsername(secrets.smtpServerUserName)
            .withSMTPServerPassword(secrets.smtpServerPassword)
            .buildMailer()
    }
    single<EmailService> { DefaultEmailService(get()) }
}