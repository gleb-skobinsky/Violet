package org.violet.violetapp.common.network

import coil3.PlatformContext
import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.w3c.dom.events.Event

actual class ConnectivityStatus actual constructor(context: PlatformContext) {
    private val _networkStateFlow = MutableStateFlow(
        if (window.navigator.onLine) {
            ConnectivityStatusState.CONNECTED
        } else {
            ConnectivityStatusState.DISCONNECTED
        }
    )
    actual val networkStateFlow = _networkStateFlow.asStateFlow()

    actual val networkState: ConnectivityStatusState
        get() = _networkStateFlow.value

    private val onlineListener: (Event) -> Unit = {
        _networkStateFlow.value = ConnectivityStatusState.CONNECTED
    }

    private val offlineListener: (Event) -> Unit = {
        _networkStateFlow.value = ConnectivityStatusState.DISCONNECTED
    }

    init {
        window.addEventListener("online", onlineListener)
        window.addEventListener("offline", offlineListener)
    }
}