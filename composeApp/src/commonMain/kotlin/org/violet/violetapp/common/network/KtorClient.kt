package org.violet.violetapp.common.network

import org.violet.violetapp.auth.data.UserSecureStorage
import org.violet.violetapp.init.presentation.InitAction
import org.violet.violetapp.init.presentation.InitStateController
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val NETWORK_TIMEOUT = 30_000L

fun configureKtorClient(
    json: Json,
    userSecureStorage: UserSecureStorage,
    initStateController: InitStateController
) = HttpClient {
    expectSuccess = false
    install(Logging) {
        level = LogLevel.ALL
    }

    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = "lk-wl-stage.etoplatezhi.ru"
        }
        bearerAuth(userSecureStorage.getToken().orEmpty())
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