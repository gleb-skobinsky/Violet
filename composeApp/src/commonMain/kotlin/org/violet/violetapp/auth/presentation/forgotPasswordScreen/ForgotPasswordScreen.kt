package org.violet.violetapp.auth.presentation.forgotPasswordScreen

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import org.violet.uiKit.theme.LocalVioletTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.violet.violetapp.auth.domain.entities.OtpMessageType
import org.violet.violetapp.auth.presentation.loginScreen.BigAuthText
import org.violet.violetapp.auth.presentation.loginScreen.VioletAppLogo
import org.violet.violetapp.auth.presentation.signupScreen.PasswordConditions
import org.violet.violetapp.common.icons.Lock
import org.violet.violetapp.common.icons.LockHidden
import org.violet.violetapp.common.icons.Message
import org.violet.violetapp.common.icons.Sms
import org.violet.violetapp.common.mvi.CollectEffects
import org.violet.violetapp.common.navigation.LocalKmpNavigator
import org.violet.violetapp.common.navigation.Screens
import org.violet.violetapp.common.presentation.RootSnackbarController
import org.violet.violetapp.common.presentation.components.VioletAppBottomSheetScaffold
import org.violet.violetapp.common.presentation.components.VioletAppBottomSheetState
import org.violet.violetapp.common.presentation.components.VioletAppButton
import org.violet.violetapp.common.presentation.components.VioletAppPasswordTextField
import org.violet.violetapp.common.presentation.components.VioletAppSurface
import org.violet.violetapp.common.presentation.components.VioletAppTextField
import org.violet.violetapp.common.presentation.components.VerticalSpacer
import org.violet.violetapp.common.presentation.components.rememberVioletAppBottomSheetState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.violet.violetapp.resources.AppRes
import org.violet.violetapp.resources.email
import org.violet.violetapp.resources.enter_otp_email
import org.violet.violetapp.resources.enter_otp_sms
import org.violet.violetapp.resources.make_up_new_password
import org.violet.violetapp.resources.otp_code_placeholder
import org.violet.violetapp.resources.password_restoration
import org.violet.violetapp.resources.repeat_password
import org.violet.violetapp.resources.restore_password
import org.violet.violetapp.resources.send_otp_code

@Composable
fun ForgotPasswordScreen(
    email: String? = null,
    viewModel: ForgotPasswordViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current
    val navigator = LocalKmpNavigator.current
    val bottomSheetState = rememberVioletAppBottomSheetState()
    viewModel.CollectEffects { effect ->
        when (effect) {
            is ForgotPasswordEffect.ShowError -> RootSnackbarController.showSnackbar(effect.message)

            ForgotPasswordEffect.ShowBottomSheet -> {
                focusManager.clearFocus(true)
                bottomSheetState.expand()
            }

            ForgotPasswordEffect.Success -> {
                navigator.replaceAll(Screens.LoginScreen)
            }

            ForgotPasswordEffect.HideBottomSheet -> {
                focusManager.clearFocus(true)
                bottomSheetState.hide()
            }
        }
    }
    // to ensure state consistency,
    // we update email once on composition start
    LaunchedEffect(Unit) {
        email?.let { viewModel.onAction(ForgotPasswordAction.UpdateEmail(it)) }
    }
    val state by viewModel.state.collectAsState()
    ForgotPasswordScreenContent(
        state = state,
        onAction = viewModel::onAction,
        bottomSheetState = bottomSheetState,
        hasEmail = email != null
    )
}

@Composable
fun ForgotPasswordScreenContent(
    state: ForgotPasswordState,
    onAction: (ForgotPasswordAction) -> Unit,
    bottomSheetState: VioletAppBottomSheetState,
    hasEmail: Boolean = false
) {
    VioletAppBottomSheetScaffold(
        sheetState = bottomSheetState,
        sheetHeightFraction = .5f,
        bottomSheetContent = {
            OtpSheetContent(state.otpType, state.otpCode, onAction)
        },
        bottomSheetColor = LocalVioletTheme.colors.surface
    ) { _, _ ->
        VioletAppSurface {
            22.dp.VerticalSpacer()
            VioletAppLogo()
            50.dp.VerticalSpacer()
            BigAuthText(stringResource(AppRes.string.password_restoration))
            40.dp.VerticalSpacer()
            if (!hasEmail) {
                VioletAppTextField(
                    value = state.email,
                    onValueChange = {
                        onAction(ForgotPasswordAction.UpdateEmail(it))
                    },
                    leftIcon = Sms,
                    placeholder = stringResource(AppRes.string.email)
                )
                24.dp.VerticalSpacer()
            }
            VioletAppPasswordTextField(
                value = state.password,
                onValueChange = {
                    onAction(ForgotPasswordAction.UpdateNewPassword(it))
                },
                leftIcon = Lock,
                placeholder = stringResource(AppRes.string.make_up_new_password)
            )
            24.dp.VerticalSpacer()
            VioletAppPasswordTextField(
                value = state.repeatPassword,
                onValueChange = {
                    onAction(ForgotPasswordAction.UpdateRepeatPassword(it))
                },
                leftIcon = LockHidden,
                placeholder = stringResource(AppRes.string.repeat_password)
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
                label = stringResource(AppRes.string.restore_password),
                modifier = Modifier.fillMaxWidth(),
                enabled = state.canResetPassword,
                isLoading = state.isLoading
            ) {
                onAction(ForgotPasswordAction.SendOtp)
            }
            24.dp.VerticalSpacer()
        }
    }
}

@Composable
private fun ColumnScope.OtpSheetContent(
    type: OtpMessageType,
    otpCode: String,
    onAction: (ForgotPasswordAction) -> Unit,
) {
    Text(
        text = stringResource(
            when (type) {
                OtpMessageType.EMAIL -> AppRes.string.enter_otp_email
                OtpMessageType.SMS -> AppRes.string.enter_otp_sms
            }
        ),
        modifier = Modifier.align(Alignment.CenterHorizontally),
        style = LocalVioletTheme.typography.bodyLarge,
        color = LocalVioletTheme.colors.primaryContainer
    )
    24.dp.VerticalSpacer()
    VioletAppTextField(
        value = otpCode,
        placeholder = stringResource(AppRes.string.otp_code_placeholder),
        onValueChange = { newVal ->
            onAction(ForgotPasswordAction.UpdateOtpCode(newVal))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textColor = LocalVioletTheme.colors.primaryContainer,
        placeholderColor = LocalVioletTheme.colors.secondaryContainer,
        backgroundColor = LocalVioletTheme.colors.tertiary,
        leftIcon = Message
    )
    40.dp.VerticalSpacer()
    VioletAppButton(
        modifier = Modifier.fillMaxWidth(),
        enabled = otpCode.isNotBlank(),
        label = stringResource(AppRes.string.send_otp_code)
    ) {
        onAction(ForgotPasswordAction.CheckOtp)
    }
}