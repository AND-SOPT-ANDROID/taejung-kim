package org.sopt.and.presentation.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.UserLoginRequest
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.core.BaseViewModel
import javax.inject.Inject
import org.sopt.and.R
import retrofit2.HttpException

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<LogInState, LogInEffect, LogInEvent>() {

    override fun createInitialState(): LogInState = LogInState()

    override suspend fun handleEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.UsernameChanged -> {
                setState { copy(username = event.username) }
            }

            is LogInEvent.PasswordChanged -> {
                setState { copy(password = event.password) }
            }

            is LogInEvent.LogInClicked -> {
                logIn()
            }
        }
    }


    // 로그인 로직
    fun logIn() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            val state = uiState.value
            val result = userRepository.postUserLogin(
                UserLoginRequest(
                    username = state.username,
                    password = state.password
                )
            )
            result.fold(
                onSuccess = {
                    setState { copy(isLoading = false) }
                    setSideEffect { LogInEffect.NavigateToMain }
                },
                onFailure = { error ->
                    val message = if (error is HttpException) {
                        when (error.code()) {
                            400 -> R.string.log_in_method
                            403 -> R.string.sign_up_paswd
                            else -> R.string.unknown_error
                        }
                    } else {
                        R.string.unknown_error
                    }
                    setState { copy(isLoading = false) }
                    setSideEffect { LogInEffect.ShowSnackBar(message) }
                }
            )
        }
    }

}