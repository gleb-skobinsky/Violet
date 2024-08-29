package org.violet.violetapp.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel

@Composable
inline fun <reified VM : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavHostController,
): VM {
    val parentEntry = remember(this) {
        try {
            destination.parent?.route
                ?.let { navController.getBackStackEntry(it) }
                ?: this
        } catch (e: Exception) {
            this
        }
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}

@Composable
inline fun <reified VM : ViewModel> NavBackStackEntry.koinViewModel(): VM =
    koinViewModel(viewModelStoreOwner = this)
