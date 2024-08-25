package com.violet

import com.violet.email.data.AppSecrets
import com.violet.email.emailKoinModule
import com.violet.plugins.*
import com.violet.users.usersModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.dsl.module
import org.koin.ktor.ext.get
import org.koin.ktor.plugin.koin

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::module
    ).start(true)
}

fun Application.module() {
    configureKoin(AppSecrets.fromEnvironment())
    configureHTTP()
    configureSockets()
    configureSerialization()
    configureDatabases(get())
    configureMonitoring()
    configureSecurity(get(), get(), get())
    configureRouting()
}

private fun Application.configureKoin(secrets: AppSecrets) {
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
