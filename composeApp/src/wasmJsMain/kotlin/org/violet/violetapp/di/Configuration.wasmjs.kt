package org.violet.violetapp.di

import org.koin.core.scope.Scope
import org.violet.violetapp.common.network.ConnectivityStatus
import org.violet.violetapp.common.network.WasmConnectivityStatus

actual fun Scope.getConnectivityStatus(): ConnectivityStatus {
    return WasmConnectivityStatus()
}