package org.violet.violetapp.secureStorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.component.KoinComponent
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(
    ExperimentalSettingsApi::class,
    ExperimentalSettingsImplementation::class
)
internal actual class VaultDelegate {
    private val platformStorage = createDataStore()


    actual val settings: SuspendSettings = DataStoreSettings(platformStorage)
}


internal actual fun KoinComponent.getVaultDelegate(): VaultDelegate {
    return VaultDelegate()
}

@OptIn(ExperimentalForeignApi::class)
fun createDataStore(): DataStore<Preferences> = createDataStore(
    NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )!!.path + "/$dataStoreFileName"
)