package org.violet.violetapp.auth

import org.violet.violetapp.auth.domain.AuthRepository
import org.violet.violetapp.auth.data.AuthRepositoryImpl
import org.violet.violetapp.auth.presentation.LoginViewModel
import org.violet.violetapp.auth.presentation.forgotPasswordScreen.ForgotPasswordViewModel
import org.violet.violetapp.auth.presentation.signupScreen.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    viewModel { LoginViewModel() }
    viewModel { SignupViewModel() }
    viewModel { ForgotPasswordViewModel() }
}