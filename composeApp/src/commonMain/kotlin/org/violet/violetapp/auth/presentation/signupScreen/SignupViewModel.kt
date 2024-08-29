package org.violet.violetapp.auth.presentation.signupScreen

import androidx.lifecycle.viewModelScope
import org.violet.violetapp.auth.domain.AuthRepository
import org.violet.violetapp.common.mvi.BaseAction
import org.violet.violetapp.common.mvi.BaseEffect
import org.violet.violetapp.common.mvi.BaseState
import org.violet.violetapp.common.mvi.BaseViewModel
import org.violet.violetapp.common.network.onError
import org.violet.violetapp.common.network.onSuccess
import org.violet.violetapp.common.utils.mapDistinctBy
import org.violet.violetapp.common.utils.onlyLatin
import org.violet.violetapp.common.utils.passwordsMatch
import org.violet.violetapp.common.utils.satisfiesMinLength
import org.violet.violetapp.common.utils.upperCaseNumberOrSpecialSymbol
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class SignupViewModel : BaseViewModel<SignupState, SignupAction, SignupEffect>(SignupState()) {
    private val authRepository: AuthRepository by inject()

    override suspend fun performOnAction(action: SignupAction) {
        when (action) {
            is SignupAction.UpdateEmail -> setState { it.copy(email = action.email) }
            is SignupAction.UpdatePassword -> setState { it.copy(password = action.value) }
            is SignupAction.UpdateRepeatPassword -> setState { it.copy(repeatPassword = action.value) }
            SignupAction.DoSignUp -> {
                authRepository.register(currentState.email, currentState.password)
                    .onSuccess {
                        setEffect(SignupEffect.Success)
                    }
                    .onError {
                        setEffect(SignupEffect.ShowError(it.message))
                    }
            }
        }
    }

    init {
        viewModelScope.launch {
            state
                .mapDistinctBy { it.password + it.repeatPassword }
                .collectLatest {
                    setState {
                        it.copy(
                            passwordsMatch = (it.password to it.repeatPassword).passwordsMatch(),
                            onlyLatin = it.password.onlyLatin(),
                            longEnough = it.password.satisfiesMinLength(8),
                            specialSymbols = it.password.upperCaseNumberOrSpecialSymbol()
                        )
                    }
                }
        }
    }
}

data class SignupState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val passwordsMatch: Boolean = false,
    val onlyLatin: Boolean = false,
    val longEnough: Boolean = false,
    val specialSymbols: Boolean = false
) : BaseState {
    val canRegister = passwordsMatch && onlyLatin && longEnough && specialSymbols
}

sealed interface SignupAction : BaseAction {
    data class UpdateEmail(val email: String) : SignupAction
    data class UpdatePassword(val value: String) : SignupAction
    data class UpdateRepeatPassword(val value: String) : SignupAction
    data object DoSignUp : SignupAction
}

sealed interface SignupEffect : BaseEffect {
    data class ShowError(val message: String) : SignupEffect
    data object Success : SignupEffect
}