package org.sopt.and.presentation.mypage

sealed class HobbyState {
    data object Idle: HobbyState()
    data object Loading: HobbyState()
    data class Success(val hobby: String): HobbyState()
    data class Failure(val message: String): HobbyState()
}