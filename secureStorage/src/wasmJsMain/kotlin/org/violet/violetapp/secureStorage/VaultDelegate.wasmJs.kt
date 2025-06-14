package org.violet.violetapp.secureStorage

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import org.koin.core.component.KoinComponent

@OptIn(ExperimentalSettingsApi::class)
internal actual class VaultDelegate {
    actual val settings: SuspendSettings = StorageSettings().toSuspendSettings()
}

internal actual fun KoinComponent.getVaultDelegate(): VaultDelegate {
    return VaultDelegate()
}