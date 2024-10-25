package org.sopt.and.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.R
import org.sopt.and.domain.SharedPreferenceManager

class UserViewModel : ViewModel() {
    private var userId = ""
    private var userPassword = ""

    // 로그인 상태를 관리하기 위한 LiveData
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    // 회원가입 로직
    fun signUp(id: String, password: String, onResult: (Boolean, Int) -> Unit) {
        when (checkSignUpValue(id, password)) {
            "idError" -> onResult(false, R.string.sign_up_error)
            "passwdError" -> onResult(false, R.string.sign_up_paswd)
            else -> {
                userId = id
                userPassword = password
                onResult(true, 0)
            }
        }
    }

    // 로그인 로직
    fun logIn(id: String, password: String) {
        if (id == userId && password == userPassword) {
            _loginResult.value = true  // 로그인 성공
            SharedPreferenceManager.saveUserId(userId)
        } else {
            _loginResult.value = false // 로그인 실패
        }
    }
}