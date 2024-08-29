package org.violet.violetapp.di

import coil3.PlatformContext
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.violet.violetapp.auth.authModule
import org.violet.violetapp.auth.data.UserSecureStorage
import org.violet.violetapp.auth.data.UserSecureStorageImpl
import org.violet.violetapp.common.network.ApiNetworkClient
import org.violet.violetapp.common.network.ConnectivityStatus
import org.violet.violetapp.common.network.configureKtorClient
import org.violet.violetapp.init.initModule
import org.violet.violetapp.secureStorage.Vault
import org.violet.violetapp.secureStorage.VaultDebugImpl

private fun coreModule(context: PlatformContext) = module {
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single<Vault> { VaultDebugImpl() }
    single<UserSecureStorage> { UserSecureStorageImpl(get()) }
    single<HttpClient> {
        configureKtorClient(get(), get(), get())
    }
    single { ConnectivityStatus(context) }
    single { ApiNetworkClient(get(), get()) }
}

fun KoinApplication.configureModules(context: PlatformContext) {
    modules(
        coreModule(context),
        authModule,
        initModule
    )
}