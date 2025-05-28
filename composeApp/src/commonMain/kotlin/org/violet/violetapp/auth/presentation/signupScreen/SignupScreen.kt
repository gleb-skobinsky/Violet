package org.violet.violetapp.auth.presentation.signupScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import org.violet.uiKit.theme.LocalVioletTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.violet.violetapp.auth.presentation.loginScreen.AuthTopTwoButtons
import org.violet.violetapp.auth.presentation.loginScreen.VioletAppLogo
import org.violet.violetapp.common.icons.Lock
import org.violet.violetapp.common.icons.LockHidden
import org.violet.violetapp.common.icons.Sms
import org.violet.violetapp.common.mvi.CollectEffects
import org.violet.violetapp.common.navigation.KMPNavigator
import org.violet.violetapp.common.navigation.LocalKmpNavigator
import org.violet.violetapp.common.navigation.Screens
import org.violet.violetapp.common.presentation.RootSnackbarController
import org.violet.violetapp.common.presentation.components.VioletAppButton
import org.violet.violetapp.common.presentation.components.VioletAppPasswordTextField
import org.violet.violetapp.common.presentation.components.VioletAppSurface
import org.violet.violetapp.common.presentation.components.VioletAppTextField
import org.violet.violetapp.common.presentation.components.VerticalSpacer
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.violet.violetapp.resources.AppRes
import org.violet.violetapp.resources.enter_email
import org.violet.violetapp.resources.log_in
import org.violet.violetapp.resources.make_up_password
import org.violet.violetapp.resources.only_latin_symbols
import org.violet.violetapp.resources.password_contains_special_symbols
import org.violet.violetapp.resources.password_is_long_enough
import org.violet.violetapp.resources.passwords_match
import org.violet.violetapp.resources.repeat_password
import org.violet.violetapp.resources.sign_up

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = koinViewModel()
) {
    val navigator = LocalKmpNavigator.current
    val state by viewModel.state.collectAsState()
    viewModel.CollectEffects {
        when (it) {
            is SignupEffect.ShowError -> RootSnackbarController.showSnackbar(it.message)
            SignupEffect.Success -> navigator.replaceAll(Screens.HomeScreen)
        }
    }
    SignupScreenContent(
        state = state,
        onAction = viewModel::onAction,
        navigator = navigator
    )
}

@Composable
fun SignupScreenContent(
    state: SignupState,
    onAction: (SignupAction) -> Unit,
    navigator: KMPNavigator
) {
    VioletAppSurface {
        22.dp.VerticalSpacer()
        VioletAppLogo()
        50.dp.VerticalSpacer()
        AuthTopTwoButtons(AppRes.string.sign_up, AppRes.string.log_in) {
            navigator.goTo(Screens.LoginScreen)
        }
        40.dp.VerticalSpacer()
        VioletAppTextField(
            value = state.email,
            onValueChange = {
                onAction(SignupAction.UpdateEmail(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(AppRes.string.enter_email),
            leftIcon = Sms,
        )
        24.dp.VerticalSpacer()
        VioletAppPasswordTextField(
            value = state.password,
            onValueChange = {
                onAction(SignupAction.UpdatePassword(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(AppRes.string.make_up_password),
            leftIcon = Lock
        )
        24.dp.VerticalSpacer()
        VioletAppPasswordTextField(
            value = state.repeatPassword,
            onValueChange = {
                onAction(SignupAction.UpdateRepeatPassword(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(AppRes.string.repeat_password),
            leftIcon = LockHidden
        )
        24.dp.VerticalSpacer()
        PasswordConditions(
            passwordsMatch = state.passwordsMatch,
            containOnlyLatin = state.onlyLatin,
            isLongEnough = state.longEnough,
            hasSpecialSymbols = state.specialSymbols
        )
        40.dp.VerticalSpacer()
        VioletAppButton(
            label = stringResource(AppRes.string.sign_up),
            modifier = Modifier.fillMaxWidth(),
            enabled = state.canRegister
        ) {
            onAction(SignupAction.DoSignUp)
        }
        24.dp.VerticalSpacer()
    }
}

@Composable
internal fun PasswordConditions(
    passwordsMatch: Boolean,
    containOnlyLatin: Boolean,
    isLongEnough: Boolean,
    hasSpecialSymbols: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PasswordCondition(AppRes.string.passwords_match, passwordsMatch)
        PasswordCondition(AppRes.string.only_latin_symbols, containOnlyLatin)
        PasswordCondition(AppRes.string.password_is_long_enough, isLongEnough)
        PasswordCondition(
            AppRes.string.password_contains_special_symbols,
            hasSpecialSymbols
        )
    }
}

@Composable
internal fun PasswordCondition(
    label: StringResource,
    isSatisfied: Boolean
) {
    Row {
        Text(
            text = "• ",
            style = LocalVioletTheme.typography.bodySmall,
            color = if (isSatisfied)
                LocalVioletTheme.colors.primary
            else
                LocalVioletTheme.colors.secondaryContainer
        )
        Text(
            text = stringResource(label),
            style = LocalVioletTheme.typography.bodySmall,
            color = if (isSatisfied)
                LocalVioletTheme.colors.primaryContainer
            else
                LocalVioletTheme.colors.secondaryContainer
        )
    }
}