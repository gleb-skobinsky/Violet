package org.violet.violetapp.common.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

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

    val currentEntry: Flow<NavBackStackEntry?>

    fun hasScreen(screen: Screens): Boolean
}

class KMPNavigatorImpl(
    private val navController: NavController
) : KMPNavigator {

    override val currentEntry = navController.currentBackStackEntryFlow

    override fun hasScreen(screen: Screens): Boolean =
        navController.currentBackStackEntry.hasScreen(screen)

    override fun goBack() {
        ifResumed {
            runSafely {
                navController.navigateUp()
            }
        }
    }

    override fun popUntil(screen: Screens) {
        ifResumed {
            runSafely {
                navController.popBackStack(
                    route = screen,
                    inclusive = false
                )
            }
        }
    }

    override fun goTo(screen: Screens) {
        if (hasScreen(screen)) return
        ifResumed {
            runSafely {
                navController.navigate(screen)
            }
        }
    }

    override fun replace(screen: Screens) {
        if (hasScreen(screen)) return
        runSafely {
            navController.popBackStack()
            navController.navigate(screen)
        }
    }

    override fun replaceAll(screen: Screens) {
        runSafely {
            navController.navigate(screen) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }

    private inline fun ifResumed(block: () -> Unit) {
        val currentEntry = navController.currentBackStackEntry ?: return
        if (currentEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
            block()
        }
    }

    private inline fun runSafely(
        crossinline block: () -> Unit
    ) {
        runCatching {
            block()
        }.onFailure {
            it.printStackTrace()
        }
    }
}

fun NavBackStackEntry?.hasScreen(screen: Screens): Boolean {
    val route = this?.destination?.route ?: return false
    val screenName = screen::class.qualifiedName ?: return false
    return screenName in route
}