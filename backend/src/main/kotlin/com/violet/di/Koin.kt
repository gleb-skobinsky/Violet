package com.violet.di

import com.violet.email.data.AppSecrets
import com.violet.email.emailKoinModule
import com.violet.features.notes.notesModule
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.koin
import com.violet.features.users.usersModule
import com.violet.shared.databaseModule

internal fun Application.configureKoin(secrets: AppSecrets) {
    koin {
        modules(
            module {
                single<AppSecrets> { secrets }
            },
            databaseModule,
            emailKoinModule,
            usersModule,
            notesModule
        )
    }
}