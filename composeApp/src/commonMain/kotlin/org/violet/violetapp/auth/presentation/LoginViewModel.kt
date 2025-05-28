package org.violet.violetapp.auth.presentation

import org.violet.violetapp.auth.domain.AuthRepository
import org.violet.violetapp.common.mvi.BaseAction
import org.violet.violetapp.common.mvi.BaseEffect
import org.violet.violetapp.common.mvi.BaseState
import org.violet.violetapp.common.mvi.BaseViewModel
import org.violet.violetapp.common.network.onError
import org.violet.violetapp.common.network.onSuccess
import io.ktor.http.HttpStatusCode
import org.jetbrains.compose.resources.getString
import org.koin.core.component.inject
import org.violet.violetapp.resources.AppRes
import org.violet.violetapp.resources.invalid_login_or_password

class LoginViewModel : BaseViewModel<LoginState, LoginAction, LoginEffect>(LoginState()) {

    private val authRepository: AuthRepository by inject()

    override suspend fun performOnAction(action: LoginAction) {
        when (action) {
            is LoginAction.DoLogIn -> {
                setState { it.copy(isLoading = true) }
                authRepository.login(
                    email = currentState.email,
                    password = currentState.password
                )
                    .onSuccess {
                        setEffect(LoginEffect.NavigateToMain)
                    }
                    .onError {
                        if (it.httpStatus == HttpStatusCode.Unauthorized) {
                            setEffect(LoginEffect.ShowError(getString(AppRes.string.invalid_login_or_password)))
                        } else {
                            setEffect(LoginEffect.ShowError(it.message))
                        }
                    }
                setState { it.copy(isLoading = false) }
            }

            is LoginAction.SetEmail -> setState { it.copy(email = action.email) }
            is LoginAction.SetPassword -> setState { it.copy(password = action.password) }
        }
    }

}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
) : BaseState {
    val canLogin = email.isNotBlank() && password.isNotBlank()
}

sealed interface LoginAction : BaseAction {
    data object DoLogIn : LoginAction

    data class SetEmail(val email: String) : LoginAction

    data class SetPassword(val password: String) : LoginAction
}

sealed interface LoginEffect : BaseEffect {
    data class ShowError(val message: String) : LoginEffect

    data object NavigateToMain : LoginEffect
}