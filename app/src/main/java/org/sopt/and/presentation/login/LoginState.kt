package org.sopt.and.presentation.login

sealed class LoginState {
    data object Idle: LoginState()
    data object Loading: LoginState()
    data class Success(val data: String): LoginState()
    data class Failure(val code: String): LoginState()
}