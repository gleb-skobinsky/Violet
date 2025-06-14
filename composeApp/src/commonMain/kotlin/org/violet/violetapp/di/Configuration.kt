package org.violet.violetapp.di

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.violet.violetapp.auth.authModule
import org.violet.violetapp.auth.data.UserSecureStorage
import org.violet.violetapp.auth.data.UserSecureStorageImpl
import org.violet.violetapp.common.network.ApiNetworkClient
import org.violet.violetapp.common.network.ConnectivityStatus
import org.violet.violetapp.common.network.configureKtorClient
import org.violet.violetapp.init.initModule
import org.violet.violetapp.secureStorage.Vault
import org.violet.violetapp.secureStorage.VaultImpl

expect fun Scope.getConnectivityStatus(): ConnectivityStatus

private val coreModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single<Vault> { VaultImpl() }
    single<UserSecureStorage> { UserSecureStorageImpl(get()) }
    single<HttpClient> {
        configureKtorClient(get(), get(), get())
    }
    single<ConnectivityStatus> { getConnectivityStatus() }
    single { ApiNetworkClient(get(), get()) }
}

fun KoinApplication.configureModules() {
    modules(
        coreModule,
        authModule,
        initModule
    )
}