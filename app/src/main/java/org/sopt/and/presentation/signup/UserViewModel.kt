package org.sopt.and.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.SharedPreferenceManager

class UserViewModel : ViewModel() {
    private var userId = ""
    private var userPassword = ""

    // 로그인 상태를 관리하기 위한 LiveData
    private val _loginState = MutableSharedFlow<LogInState>()
    val loginState = _loginState.asSharedFlow()

    private val _signUpState = MutableSharedFlow<SignUpState>()
    val signUpState = _signUpState.asSharedFlow()

    // 회원가입 로직
    fun signUp(id: String, password: String) {
        viewModelScope.launch {
            when (checkSignUpValue(id, password)) {
                "idError" -> _signUpState.emit(SignUpState.Error(R.string.sign_up_error))
                "passwdError" -> _signUpState.emit(SignUpState.Error(R.string.sign_up_paswd))
                else -> {
                    userId = id
                    userPassword = password
                    _signUpState.emit(SignUpState.Success)
                }
            }
        }
    }

    // 로그인 로직
    fun logIn(id: String, password: String) {
        viewModelScope.launch {
            if (id == userId && password == userPassword) {
                _loginState.emit(LogInState.Success)
                SharedPreferenceManager.saveUserId(userId)
            } else {
                _loginState.emit(LogInState.Error)
            }
        }
    }

    sealed class SignUpState {
        object Success : SignUpState()
        data class Error(val messageResId: Int) : SignUpState()
    }

    sealed class LogInState {
        object Success : LogInState()
        object Error : LogInState()
    }
}