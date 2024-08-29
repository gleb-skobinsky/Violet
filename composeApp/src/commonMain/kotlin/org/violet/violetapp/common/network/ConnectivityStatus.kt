package org.violet.violetapp.common.network

import coil3.PlatformContext
import kotlinx.coroutines.flow.StateFlow

expect class ConnectivityStatus(context: PlatformContext) {
    val networkStateFlow: StateFlow<ConnectivityStatusState>
    val networkState: ConnectivityStatusState
}

enum class ConnectivityStatusState(val connected: Boolean) {
    UNKNOWN(false),
    DISCONNECTED(false),
    CONNECTED(true)
}