package org.violet.violetapp.secureStorage

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.SuspendSettings
import org.koin.core.component.KoinComponent

@OptIn(
    ExperimentalSettingsApi::class,
    ExperimentalSettingsImplementation::class
)
internal expect class VaultDelegate {
    val settings: SuspendSettings
}

internal expect fun KoinComponent.getVaultDelegate(): VaultDelegate