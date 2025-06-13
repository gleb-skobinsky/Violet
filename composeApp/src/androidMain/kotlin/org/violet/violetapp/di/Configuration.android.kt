package org.violet.violetapp.di

import org.koin.core.scope.Scope
import org.violet.violetapp.common.network.AndroidConnectivityStatus
import org.violet.violetapp.common.network.ConnectivityStatus

actual fun Scope.getConnectivityStatus(): ConnectivityStatus {
    return AndroidConnectivityStatus(context = get())
}