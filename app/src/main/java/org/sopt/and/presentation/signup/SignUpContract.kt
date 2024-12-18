package org.sopt.and.presentation.signup

import org.sopt.and.presentation.core.UiEvent
import org.sopt.and.presentation.core.UiSideEffect
import org.sopt.and.presentation.core.UiState

// State 정의
data class SignUpState(
    val username: String = "",
    val password: String = "",
    val hobby: String = "",
    val isLoading: Boolean = false
) : UiState

// Event 정의
sealed class SignUpEvent : UiEvent {
    data class UsernameChanged(val username: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class HobbyChanged(val hobby: String) : SignUpEvent()
    object SignUpClicked : SignUpEvent()
}

// SideEffect 정의
sealed class SignUpSideEffect : UiSideEffect {
    data class ShowToast(val message: String) : SignUpSideEffect()
    object NavigateToLogin : SignUpSideEffect()
}