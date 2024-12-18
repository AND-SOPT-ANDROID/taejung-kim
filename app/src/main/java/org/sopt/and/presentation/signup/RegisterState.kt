package org.sopt.and.presentation.signup

sealed class RegisterState {
    data object Idle: RegisterState()
    data object Loading: RegisterState()
    data class Success(val token: Int): RegisterState()
    data class Failure(val code: String): RegisterState()
}