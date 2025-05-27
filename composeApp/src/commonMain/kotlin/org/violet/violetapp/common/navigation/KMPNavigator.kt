package org.violet.violetapp.common.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.violet.violetapp.common.utils.QualifiedName

val LocalKmpNavigator = staticCompositionLocalOf<KMPNavigator> {
    error("No KMPNavigator provided")
}

interface KMPNavigator {

    /**
     * Safely navigates back on back button click.
     */
    fun goBack()

    /**
     * Safely navigates to the given screen.
     */
    fun goTo(screen: Screens)

    fun replace(screen: Screens)

    fun replaceAll(screen: Screens)

    fun popUntil(screen: Screens)

    val currentEntry: StateFlow<NavBackStackEntry?>

    fun hasScreen(screen: Screens): Boolean
}

class KMPNavigatorImpl(private val navController: NavController) :
    KMPNavigator {
    private val coroutineScope =
        CoroutineScope(Dispatchers.Main + SupervisorJob())

    override val currentEntry = navController.currentBackStackEntryFlow
        .stateIn(coroutineScope, SharingStarted.Eagerly, null)

    override fun hasScreen(screen: Screens): Boolean =
        navController.currentBackStackEntry.hasScreen(screen)

    override fun goBack() {
        ifResumed {
            runWithMain {
                navController.navigateUp()
            }
        }
    }

    override fun popUntil(screen: Screens) {
        ifResumed {
            runWithMain {
                navController.popBackStack(
                    route = screen.QualifiedName,
                    inclusive = false
                )
            }
        }
    }

    override fun goTo(screen: Screens) {
        if (hasScreen(screen)) return
        ifResumed {
            runWithMain {
                navController.typedNavigate(screen)
            }
        }
    }

    override fun replace(screen: Screens) {
        if (hasScreen(screen)) return
        runWithMain {
            navController.popBackStack()
            navController.typedNavigate(screen)
        }
    }

    override fun replaceAll(screen: Screens) {
        runWithMain {
            navController.typedNavigate(screen) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }

    private fun ifResumed(block: () -> Unit) {
        val currentEntry = navController.currentBackStackEntry ?: return
        if (currentEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
            block()
        }
    }

    private fun runWithMain(block: () -> Unit) = coroutineScope.launch {
        runCatching {
            block()
        }.onFailure {
            it.printStackTrace()
        }
    }
}

fun NavBackStackEntry?.hasScreen(screen: Screens): Boolean {
    val route = this?.destination?.route ?: return false
    return screen.QualifiedName in route
}