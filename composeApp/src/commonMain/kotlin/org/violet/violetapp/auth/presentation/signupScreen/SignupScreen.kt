package org.violet.violetapp.auth.presentation.signupScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
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
import violet.composeapp.generated.resources.Res
import violet.composeapp.generated.resources.enter_email
import violet.composeapp.generated.resources.log_in
import violet.composeapp.generated.resources.make_up_password
import violet.composeapp.generated.resources.only_latin_symbols
import violet.composeapp.generated.resources.password_contains_special_symbols
import violet.composeapp.generated.resources.password_is_long_enough
import violet.composeapp.generated.resources.passwords_match
import violet.composeapp.generated.resources.repeat_password
import violet.composeapp.generated.resources.sign_up

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
        AuthTopTwoButtons(Res.string.sign_up, Res.string.log_in) {
            navigator.goTo(Screens.LoginScreen)
        }
        40.dp.VerticalSpacer()
        VioletAppTextField(
            value = state.email,
            onValueChange = {
                onAction(SignupAction.UpdateEmail(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(Res.string.enter_email),
            leftIcon = Sms,
        )
        24.dp.VerticalSpacer()
        VioletAppPasswordTextField(
            value = state.password,
            onValueChange = {
                onAction(SignupAction.UpdatePassword(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(Res.string.make_up_password),
            leftIcon = Lock
        )
        24.dp.VerticalSpacer()
        VioletAppPasswordTextField(
            value = state.repeatPassword,
            onValueChange = {
                onAction(SignupAction.UpdateRepeatPassword(it))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(Res.string.repeat_password),
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
            label = stringResource(Res.string.sign_up),
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
        PasswordCondition(Res.string.passwords_match, passwordsMatch)
        PasswordCondition(Res.string.only_latin_symbols, containOnlyLatin)
        PasswordCondition(Res.string.password_is_long_enough, isLongEnough)
        PasswordCondition(
            Res.string.password_contains_special_symbols,
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
            style = MaterialTheme.typography.bodySmall,
            color = if (isSatisfied)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondaryContainer
        )
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodySmall,
            color = if (isSatisfied)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        )
    }
}