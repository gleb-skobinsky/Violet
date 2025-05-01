package com.violet

import com.violet.di.configureKoin
import com.violet.email.data.AppSecrets
import com.violet.features.auth.configureAuth
import com.violet.features.users.configureUsersRoutes
import com.violet.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.get

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::module
    ).start(true)
}

fun Application.module() {
    val secrets = AppSecrets.fromEnvironment()
    configureKoin(secrets)
    configureSwagger(secrets)
    configureSockets()
    configureSerialization()
    configureAuth(secrets, get(), get())
    configureUsersRoutes(get())
    configureMonitoring()
    configureStaticFiles()
}
