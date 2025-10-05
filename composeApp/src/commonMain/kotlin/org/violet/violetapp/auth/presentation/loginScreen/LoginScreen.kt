package org.violet.violetapp.auth.presentation.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.violet.uiKit.theme.LocalVioletTheme
import org.violet.uiKit.theme.VioletTheme
import org.violet.violetapp.auth.presentation.LoginAction
import org.violet.violetapp.auth.presentation.LoginEffect
import org.violet.violetapp.auth.presentation.LoginState
import org.violet.violetapp.auth.presentation.LoginViewModel
import org.violet.violetapp.common.icons.Lock
import org.violet.violetapp.common.icons.Sms
import org.violet.violetapp.common.mvi.CollectEffects
import org.violet.violetapp.common.navigation.KMPNavigator
import org.violet.violetapp.common.navigation.KMPNavigator.Companion.PreviewNavigator
import org.violet.violetapp.common.navigation.LocalKmpNavigator
import org.violet.violetapp.common.navigation.Screens
import org.violet.violetapp.common.presentation.RootSnackbarController
import org.violet.violetapp.common.presentation.components.VerticalSpacer
import org.violet.violetapp.common.presentation.components.VioletAppButton
import org.violet.violetapp.common.presentation.components.VioletAppPasswordTextField
import org.violet.violetapp.common.presentation.components.VioletAppSurface
import org.violet.violetapp.common.presentation.components.VioletAppTextField
import org.violet.violetapp.common.presentation.modifiers.noRippleClickable
import org.violet.violetapp.resources.AppRes
import org.violet.violetapp.resources.email
import org.violet.violetapp.resources.forgot_password
import org.violet.violetapp.resources.log_in
import org.violet.violetapp.resources.logo
import org.violet.violetapp.resources.password
import org.violet.violetapp.resources.sign_up

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val navigator = LocalKmpNavigator.current
    val state by viewModel.state.collectAsState()
    viewModel.CollectEffects {
        when (it) {
            LoginEffect.NavigateToMain -> navigator.replaceAll(Screens.HomeScreen)
            is LoginEffect.ShowError -> RootSnackbarController.showSnackbar(it.message)
        }
    }
    LoginScreenContent(
        state = state,
        onAction = viewModel::onAction,
        navigator = navigator
    )
}

@Composable
fun LoginScreenContent(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    navigator: KMPNavigator
) {
    VioletAppSurface {
        22.dp.VerticalSpacer()
        VioletAppLogo()
        50.dp.VerticalSpacer()
        AuthTopTwoButtons(AppRes.string.log_in, AppRes.string.sign_up) {
            navigator.goTo(Screens.SignupScreen)
        }
        40.dp.VerticalSpacer()
        VioletAppTextField(
            value = state.email,
            onValueChange = {
                onAction(LoginAction.SetEmail(it))
            },
            leftIcon = Sms,
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(AppRes.string.email)
        )
        24.dp.VerticalSpacer()
        VioletAppPasswordTextField(
            value = state.password,
            onValueChange = {
                onAction(LoginAction.SetPassword(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(AppRes.string.password),
            leftIcon = Lock
        )
        24.dp.VerticalSpacer()
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .noRippleClickable {
                    navigator.goTo(Screens.ForgotPasswordScreen())
                },
            text = stringResource(AppRes.string.forgot_password),
            style = LocalVioletTheme.typography.bodyMedium,
            color = LocalVioletTheme.colors.primaryContainer
        )
        40.dp.VerticalSpacer()
        VioletAppButton(
            isLoading = state.isLoading,
            label = stringResource(AppRes.string.log_in),
            modifier = Modifier.fillMaxWidth(),
            enabled = state.canLogin
        ) {
            onAction(LoginAction.DoLogIn)
        }
        24.dp.VerticalSpacer()
    }
}

@Composable
internal fun ColumnScope.VioletAppLogo() {
    Image(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .size(240.dp),
        imageVector = vectorResource(AppRes.drawable.logo),
        contentDescription = "Payeasy logo"
    )
}

@Composable
internal fun AuthTopTwoButtons(
    firstText: StringResource,
    secondText: StringResource,
    onClickSecond: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        BigAuthText(stringResource(firstText))
        BigAuthText(stringResource(secondText), true, onClickSecond)
    }
}

@Composable
fun BigAuthText(
    text: String,
    secondary: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            },
        text = text,
        color = if (secondary)
            LocalVioletTheme.colors.secondaryContainer
        else
            LocalVioletTheme.colors.primaryContainer,
        style = LocalVioletTheme.typography.headlineLarge.copy(
            fontSize = if (secondary) 24.sp else 32.sp
        )
    )
}

@Composable
@Preview
fun LoginScreenPreview() {
    VioletTheme {
        LoginScreenContent(
            state = LoginState(),
            onAction = {},
            navigator = PreviewNavigator
        )
    }
}