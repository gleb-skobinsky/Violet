import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.bundle.Bundle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.violet.violetapp.auth.presentation.forgotPasswordScreen.ForgotPasswordScreen
import org.violet.violetapp.auth.presentation.loginScreen.LoginScreen
import org.violet.violetapp.auth.presentation.signupScreen.SignupScreen
import org.violet.violetapp.common.mvi.CollectEffects
import org.violet.violetapp.common.navigation.KMPNavigator
import org.violet.violetapp.common.navigation.KMPNavigatorImpl
import org.violet.violetapp.common.navigation.LocalKmpNavigator
import org.violet.violetapp.common.navigation.Screens
import org.violet.violetapp.common.navigation.autoRouteComposable
import org.violet.violetapp.common.navigation.typedComposable
import org.violet.violetapp.common.presentation.RootSnackbarController
import org.violet.violetapp.common.presentation.components.VioletAppNavBarWrapper
import org.violet.violetapp.common.presentation.components.SnackbarScaffold
import org.violet.violetapp.common.presentation.material.VioletAppColorScheme
import org.violet.violetapp.common.presentation.material.VioletAppTypography
import org.violet.violetapp.common.utils.QualifiedName
import org.violet.violetapp.init.presentation.InitEffect
import org.violet.violetapp.init.presentation.InitStateController

private const val FAST_NAV_ANIMATION = 300

@Composable
@Preview
@NonRestartableComposable
fun App(
    initController: InitStateController = koinInject(),
    savedState: Bundle? = null
) {
    VioletAppTheme {
        val navController = rememberNavController()
        val navigator = remember { KMPNavigatorImpl(navController) }
        val haze = remember { HazeState() }
        ProvideNavigator(navigator) {
            SnackbarScaffold(
                snackbarState = RootSnackbarController.snackbarState,
                bottomBar = {
                    VioletAppNavBarWrapper(haze)
                }
            ) {
                NavHost(
                    navController = navController,
                    modifier = Modifier
                        .haze(haze)
                        .background(MaterialTheme.colorScheme.surface),
                    enterTransition = { fadeIn(tween(FAST_NAV_ANIMATION)) },
                    exitTransition = { fadeOut(tween(FAST_NAV_ANIMATION)) },
                    startDestination = Screens.LoginScreen.QualifiedName
                ) {
                    // Auth graph
                    typedComposable<Screens.LoginScreen> { LoginScreen() }
                    typedComposable<Screens.SignupScreen> { SignupScreen() }
                    autoRouteComposable<Screens.ForgotPasswordScreen> { ForgotPasswordScreen(email) }
                    typedComposable<Screens.HomeScreen> { }
                    typedComposable<Screens.FeedScreen> { }
                    typedComposable<Screens.ProfileScreen> { }
                }
                initController.CollectEffects { effect ->
                    when (effect) {
                        InitEffect.ShowContent -> {
                            // On Android, if savedInstanceState is not null,
                            // process death happened and we should let the navigation controller
                            // to restore its state.
                            // Otherwize, navigate to home screen.
                            if (savedState == null) navigator.replaceAll(Screens.HomeScreen)
                        }

                        InitEffect.ShowLogin -> navigator.replaceAll(Screens.LoginScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun VioletAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VioletAppColorScheme,
        typography = VioletAppTypography(),
        content = content
    )
}

@Composable
fun ProvideNavigator(
    navigator: KMPNavigator,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalKmpNavigator provides navigator,
        content = content
    )
}