package org.violet.violetapp.common.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import org.violet.violetapp.common.utils.QualifiedName
import org.violet.violetapp.common.utils.promisedValue
import org.violet.violetapp.common.utils.runningHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

val LocalKmpNavigator = staticCompositionLocalOf { promisedValue<KMPNavigator>() }

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

    /**
     * Allows to subscribe to pop events.
     *
     * The emitted value is the entry that has been popped.
     *
     * Mainly defined for the [BackHandler] to work in cross-platform.
     */
    val popEvents: Flow<NavBackStackEntry?>

    val currentEntry: StateFlow<NavBackStackEntry?>

    fun hasScreen(screen: Screens): Boolean
}

class KMPNavigatorImpl(private val navController: NavController) : KMPNavigator {
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override val popEvents = navController.currentBackStack
        .runningHistory()
        .filter { (prev, current) ->
            current.lastOrNull() == prev.getPenultimate()
        }
        .map { it.previous?.lastOrNull() }

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

    private fun List<NavBackStackEntry>?.getPenultimate(): NavBackStackEntry? {
        if (this == null) return null
        var entriesCount = 0
        for (entry in asReversed()) {
            if (entry.destination is ComposeNavigator.Destination) entriesCount++
            if (entriesCount > 1) return entry
        }
        return null
    }
}

fun NavBackStackEntry?.hasScreen(screen: Screens): Boolean {
    val route = this?.destination?.route ?: return false
    return screen.QualifiedName in route
}