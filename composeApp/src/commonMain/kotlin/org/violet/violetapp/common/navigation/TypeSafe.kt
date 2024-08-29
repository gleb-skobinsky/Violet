package org.violet.violetapp.common.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.ktor.http.decodeURLQueryComponent
import io.ktor.http.encodeURLQueryComponent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmSuppressWildcards
import kotlin.reflect.KClass

val navJson = Json { ignoreUnknownKeys = true }

val <T : Any> KClass<T>.QualifiedName: String
    get() = qualifiedName.orEmpty()

const val DATA_ARG = "additionalData"

inline fun <reified T : Any> NavGraphBuilder.typedComposable(
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?
    )? =
        null,
    noinline exitTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?
    )? =
        null,
    noinline popEnterTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?
    )? =
        enterTransition,
    noinline popExitTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?
    )? =
        exitTransition,
    noinline sizeTransform:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?
    )? =
        null,
    noinline content: @Composable (NavBackStackEntry) -> Unit,
) {
    val route = "${T::class.QualifiedName}?$DATA_ARG={$DATA_ARG}"
    composable(
        route = route,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        content = { content(it) }
    )
}

inline fun <reified T : Any> NavGraphBuilder.typedNavigation(
    startDestination: Any,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?
    )? =
        null,
    noinline exitTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?
    )? =
        null,
    noinline popEnterTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?
    )? =
        enterTransition,
    noinline popExitTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?
    )? =
        exitTransition,
    noinline sizeTransform:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?
    )? =
        null,
    noinline builder: NavGraphBuilder.() -> Unit,
) {
    navigation(
        startDestination = startDestination::class.QualifiedName,
        route = T::class.QualifiedName,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        builder = builder
    )
}

inline fun <reified T : Any> NavGraphBuilder.autoRouteComposable(
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?
    )? =
        null,
    noinline exitTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?
    )? =
        null,
    noinline popEnterTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?
    )? =
        enterTransition,
    noinline popExitTransition:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?
    )? =
        exitTransition,
    noinline sizeTransform:
    (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?
    )? =
        null,
    noinline content: @Composable T.(NavBackStackEntry) -> Unit,
) {
    val route = "${T::class.QualifiedName}?$DATA_ARG={$DATA_ARG}"
    composable(
        route = route,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        content = { it.toRoute<T> { content(it) } }
    )
}


inline fun <reified T : Any> NavController.typedNavigate(
    route: T,
    noinline builder: NavOptionsBuilder.() -> Unit = {},
) {
    val query = navJson
        .encodeToString<T>(route)
        .encodeURLQueryComponent()
        .replace("=", "%3D")
    val routeString =
        "${route::class.QualifiedName}?$DATA_ARG=$query"
    navigate(route = routeString, builder = builder)
}

@Composable
inline fun <reified T : Any> NavBackStackEntry.toRoute(onSuccess: @Composable T.() -> Unit) {
    val route: T? = try {
        navJson.decodeFromString<T>(
            arguments
                ?.getString(DATA_ARG).orEmpty()
                .replace("%3D", "=")
                .decodeURLQueryComponent()
        )
    } catch (e: Exception) {
        null
    }
    route?.onSuccess()
}