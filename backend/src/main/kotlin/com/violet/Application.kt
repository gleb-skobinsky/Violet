package com.violet

import com.violet.email.data.AppSecrets
import com.violet.email.emailKoinModule
import com.violet.plugins.*
import com.violet.users.usersModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.koin.ktor.ext.get
import org.koin.ktor.plugin.koin
import java.io.File
import java.nio.file.Paths

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = Application::module
    ).start(true)
}

fun Application.module() {
    val secrets = readConfig()
    configureKoin(secrets)
    configureHTTP()
    configureSockets()
    configureSerialization()
    configureDatabases(get())
    configureMonitoring()
    configureSecurity(get(), get(), get())
    configureRouting()
}

private fun readConfig(): AppSecrets {
    val appSecretsJson = Json {
        ignoreUnknownKeys = true
    }
    val path = Paths.get("").toAbsolutePath()
    val fileText = File(path.toString(), "server-config-dev.json").readText()
    return appSecretsJson.decodeFromString(fileText)
}

private fun Application.configureKoin(secrets: AppSecrets) {
    koin {
        modules(
            module {
                single<AppSecrets> { secrets }
            },
            emailKoinModule(secrets),
            usersModule(secrets)
        )
    }
}
