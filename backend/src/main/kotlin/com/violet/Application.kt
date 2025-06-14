package com.violet

import com.violet.di.configureKoin
import com.violet.email.data.AppSecrets
import com.violet.features.auth.configureAuth
import com.violet.features.notes.configureNotes
import com.violet.features.users.configureUsersRoutes
import com.violet.plugins.configureMonitoring
import com.violet.plugins.configureSerialization
import com.violet.plugins.configureSockets
import com.violet.plugins.configureStaticFiles
import com.violet.plugins.configureSwagger
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.cors.routing.CORS
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
    configureCors()
    configureKoin(secrets)
    configureSwagger(secrets)
    configureSockets()
    configureSerialization()
    configureAuth(secrets, get(), get())
    configureNotes(get())
    configureUsersRoutes(get())
    configureMonitoring()
    configureStaticFiles()
}

private fun Application.configureCors() {
    install(CORS) {
        allowHost("localhost:8082", schemes = listOf("http"))

        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowCredentials = true
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Options)
    }
}
