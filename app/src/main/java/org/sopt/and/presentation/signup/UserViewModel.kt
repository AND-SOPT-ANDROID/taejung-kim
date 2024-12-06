package org.sopt.and.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.model.UserLoginRequest
import org.sopt.and.domain.model.UserRegisterRequest
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.login.LoginState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _userRegisterState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val userRegisterState: StateFlow<RegisterState> = _userRegisterState

    private val _userLoginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val userLoginState: StateFlow<LoginState> = _userLoginState

    // 회원가입 로직
    fun signUp(username: String, password: String, hobby: String) {
        _userRegisterState.value = RegisterState.Loading
        viewModelScope.launch {
            val result = userRepository.postUserRegistering(
                UserRegisterRequest(
                    username = username, password = password, hobby = hobby
                )
            )
            _userRegisterState.value =
                result.fold(onSuccess = { RegisterState.Success(it.no) },
                    onFailure = { RegisterState.Failure(it.message ?: "") })
        }
    }

    // 로그인 로직
    fun logIn(username: String, password: String) {
        _userLoginState.value = LoginState.Loading
        viewModelScope.launch {
            val result = userRepository.postUserLogin(
                UserLoginRequest(
                    username = username, password = password
                )
            )
            _userLoginState.value = result.fold(onSuccess = { LoginState.Success(it.token) },
                onFailure = { LoginState.Failure(it.message ?: "") })
        }
    }
}