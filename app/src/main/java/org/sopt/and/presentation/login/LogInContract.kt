package org.sopt.and.presentation.login

import org.sopt.and.presentation.core.UiEvent
import org.sopt.and.presentation.core.UiSideEffect
import org.sopt.and.presentation.core.UiState

// State 정의
data class LogInState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: Int? = null
) : UiState

// Event 정의
sealed class LogInEvent : UiEvent {
    data class UsernameChanged(val username: String) : LogInEvent()
    data class PasswordChanged(val password: String) : LogInEvent()
    object LogInClicked : LogInEvent()
}

// SideEffect 정의
sealed class LogInEffect : UiSideEffect {
    data class ShowSnackBar(val message: Int) : LogInEffect()
    object NavigateToMain : LogInEffect()
}