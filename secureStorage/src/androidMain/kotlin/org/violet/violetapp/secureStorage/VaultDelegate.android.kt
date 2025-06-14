package org.violet.violetapp.secureStorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@OptIn(
    ExperimentalSettingsApi::class,
    ExperimentalSettingsImplementation::class
)
internal actual class VaultDelegate(
    context: Context
) {
    private val platformStorage = createDataStore(context)

    actual val settings: SuspendSettings = DataStoreSettings(platformStorage)
}

fun createDataStore(context: Context): DataStore<Preferences> = createDataStore(
    context.filesDir.resolve(dataStoreFileName).absolutePath
)

internal actual fun KoinComponent.getVaultDelegate(): VaultDelegate {
    return VaultDelegate(context = get())
}