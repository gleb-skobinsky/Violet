package com.violet.di

import com.violet.email.data.AppSecrets
import com.violet.email.emailKoinModule
import com.violet.users.usersModule
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.koin

internal fun Application.configureKoin(secrets: AppSecrets) {
    koin {
        modules(
            module {
                single<AppSecrets> { secrets }
            },
            emailKoinModule,
            usersModule
        )
    }
}