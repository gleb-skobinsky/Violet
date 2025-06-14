package org.violet.violetapp.common.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.violet.violetapp.auth.data.UserSecureStorage
import org.violet.violetapp.common.utils.localhost
import org.violet.violetapp.init.presentation.InitAction
import org.violet.violetapp.init.presentation.InitStateController

private const val NETWORK_TIMEOUT = 30_000L

fun configureKtorClient(
    json: Json,
    userSecureStorage: UserSecureStorage,
    initStateController: InitStateController
) = HttpClient {
    expectSuccess = false
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                println("Ktor Client: $message")
            }
        }
    }

    defaultRequest {
        url {
            protocol = localhost.protocol
            host = localhost.host
            port = localhost.port
        }
        contentType(ContentType.Application.Json)
    }

    install(HttpTimeout) {
        requestTimeoutMillis = NETWORK_TIMEOUT
        connectTimeoutMillis = NETWORK_TIMEOUT
        socketTimeoutMillis = NETWORK_TIMEOUT
    }

    install(ContentNegotiation) {
        json(json)
    }

    install(Auth) {
        bearer {
            loadTokens {
                userSecureStorage.getToken()?.let {
                    BearerTokens(
                        accessToken = it,
                        refreshToken = ""
                    )
                }
            }

            refreshTokens {
                userSecureStorage.clearAll()
                initStateController.onAction(InitAction.Logout)
                null
            }
        }
    }
}