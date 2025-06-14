package org.violet.violetapp.secureStorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.core.component.KoinComponent
import java.io.File

@OptIn(
    ExperimentalSettingsApi::class,
    ExperimentalSettingsImplementation::class
)
internal actual class VaultDelegate {

    private val platformStorage = createDataStore()

    actual val settings: SuspendSettings = DataStoreSettings(platformStorage)
}

fun createDataStore(): DataStore<Preferences> = createDataStore(
    path = run {
        val baseDir = File(
            System.getProperty("user.home"),
            ".violetapp"
        )
        baseDir.mkdirs()
        File(baseDir, dataStoreFileName).absolutePath
    }
)

internal actual fun KoinComponent.getVaultDelegate(): VaultDelegate {
    return VaultDelegate()
}