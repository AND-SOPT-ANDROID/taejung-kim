package org.sopt.and.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.SharedPreferenceManager

interface AuthState {
    class Success(val type: AuthType) : AuthState
    class Error(val messageResId: Int, val type: AuthType) : AuthState
}

enum class AuthType {
    LOGIN, SIGNUP
}

class UserViewModel : ViewModel() {
    private var userData = UserData()

    // 로그인 상태를 관리하기 위한 LiveData
    private val _authState = MutableStateFlow<AuthState?>(null)  // 초기 상태로 null 설정
    val authState: StateFlow<AuthState?> = _authState.asStateFlow()

    // 회원가입 로직
    fun signUp(id: String, password: String) {
        viewModelScope.launch {
            when (checkSignUpValue(id, password)) {
                "idError" -> _authState.value = AuthState.Error(R.string.sign_up_error, AuthType.SIGNUP)
                "passwdError" -> _authState.value = AuthState.Error(R.string.sign_up_paswd, AuthType.SIGNUP)
                else -> {
                    userData = UserData(id, password)
                    _authState.value = AuthState.Success(AuthType.SIGNUP)
                }
            }
        }
    }

    // 로그인 로직
    fun logIn(id: String, password: String) {
        viewModelScope.launch {
            if (id == userData.userId && password == userData.userPassword) {
                _authState.value = AuthState.Success(AuthType.LOGIN)
                SharedPreferenceManager.saveUserId(userData.userId)
            } else {
                _authState.value = AuthState.Error(R.string.log_in_error, AuthType.LOGIN)
            }
        }
    }
}