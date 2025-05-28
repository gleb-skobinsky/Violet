package com.violet.di

import com.violet.email.data.AppSecrets
import com.violet.email.emailKoinModule
import com.violet.features.notes.notesModule
import com.violet.features.users.usersModule
import com.violet.shared.databaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal fun configureKoin(secrets: AppSecrets) {
    startKoin {
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