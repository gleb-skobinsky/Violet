import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.bundle.Bundle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.violet.uiKit.theme.LocalVioletTheme
import org.violet.uiKit.theme.VioletTheme
import org.violet.violetapp.auth.presentation.forgotPasswordScreen.ForgotPasswordScreen
import org.violet.violetapp.auth.presentation.loginScreen.LoginScreen
import org.violet.violetapp.auth.presentation.signupScreen.SignupScreen
import org.violet.violetapp.common.mvi.CollectEffects
import org.violet.violetapp.common.navigation.KMPNavigator
import org.violet.violetapp.common.navigation.KMPNavigatorImpl
import org.violet.violetapp.common.navigation.LocalKmpNavigator
import org.violet.violetapp.common.navigation.Screens
import org.violet.violetapp.common.presentation.RootSnackbarController
import org.violet.violetapp.common.presentation.components.SnackbarScaffold
import org.violet.violetapp.common.presentation.components.VioletAppNavBarWrapper
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
    VioletTheme {
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
                        .hazeSource(haze)
                        .background(LocalVioletTheme.colors.surface),
                    enterTransition = { fadeIn(tween(FAST_NAV_ANIMATION)) },
                    exitTransition = { fadeOut(tween(FAST_NAV_ANIMATION)) },
                    startDestination = Screens.LoginScreen
                ) {
                    // Auth graph
                    composable<Screens.LoginScreen> { LoginScreen() }
                    composable<Screens.SignupScreen> { SignupScreen() }
                    composable<Screens.ForgotPasswordScreen> {
                        val route = it.toRoute<Screens.ForgotPasswordScreen>()
                        ForgotPasswordScreen(route.email)
                    }
                    composable<Screens.HomeScreen> { }
                    composable<Screens.FeedScreen> { }
                    composable<Screens.ProfileScreen> { }
                }
                initController.CollectEffects { effect ->
                    when (effect) {
                        InitEffect.ShowContent -> {
                            // On Android, if savedInstanceState is not null,
                            // process death happened and we should let the navigation controller
                            // to restore its state.
                            // Otherwize, navigate to home screen.
                            if (savedState == null) {
                                navigator.replaceAll(Screens.HomeScreen)
                            }
                        }

                        InitEffect.ShowLogin -> navigator.replaceAll(Screens.LoginScreen)
                    }
                }
            }
        }
    }
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