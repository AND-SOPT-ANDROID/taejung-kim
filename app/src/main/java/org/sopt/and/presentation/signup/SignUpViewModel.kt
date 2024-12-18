package org.sopt.and.presentation.signup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.UserLoginRequest
import org.sopt.and.domain.model.UserRegisterRequest
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.core.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<SignUpState, SignUpSideEffect, SignUpEvent>() {

    override fun createInitialState(): SignUpState = SignUpState()

    override suspend fun handleEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.UsernameChanged -> {
                setState { copy(username = event.username) }
            }

            is SignUpEvent.PasswordChanged -> {
                setState { copy(password = event.password) }
            }

            is SignUpEvent.HobbyChanged -> {
                setState { copy(hobby = event.hobby) }
            }

            is SignUpEvent.SignUpClicked -> {
                signUp()
            }
        }
    }

    // 회원가입 로직
    fun signUp() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            val state = uiState.value
            val result = userRepository.postUserRegistering(
                UserRegisterRequest(
                    username = state.username,
                    password = state.password,
                    hobby = state.hobby
                )
            )
            result.fold(
                onSuccess = {
                    setState { copy(isLoading = false) }
                    setSideEffect { SignUpSideEffect.NavigateToLogin }
                },
                onFailure = {
                    setState { copy(isLoading = false) }
                    setSideEffect { SignUpSideEffect.ShowToast(it.message ?: "회원가입 실패") }
                }
            )
        }
    }
}