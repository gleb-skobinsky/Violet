package org.violet.violetapp.secureStorage

import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.component.KoinComponent

@OptIn(ExperimentalSettingsApi::class)
class VaultImpl : Vault, KoinComponent {
    private val dao by lazy {
        getVaultDelegate()
    }

    override suspend fun string(key: String): String? {
        return dao.settings.getStringOrNull(key)
    }

    override suspend fun saveStr(key: String, value: String) {
        dao.settings.putString(key, value)
    }

    override suspend fun clear() {
        dao.settings.clear()
    }
}