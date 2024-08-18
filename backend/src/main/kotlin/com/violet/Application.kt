package com.violet

import com.violet.email.emailKoinModule
import com.violet.plugins.configureDatabases
import com.violet.plugins.configureHTTP
import com.violet.plugins.configureMonitoring
import com.violet.plugins.configureRouting
import com.violet.plugins.configureSecurity
import com.violet.plugins.configureSerialization
import com.violet.plugins.configureSockets
import com.violet.users.usersModule
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.get
import org.koin.ktor.plugin.koin

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(true)
}

fun Application.module() {
    configureKoin()
    configureHTTP()
    configureSockets()
    configureSerialization()
    configureDatabases(get())
    configureMonitoring()
    configureSecurity(get(), get())
    configureRouting()
}

private fun Application.configureKoin() {
    koin {
        modules(
            emailKoinModule,
            usersModule
        )
    }
}
