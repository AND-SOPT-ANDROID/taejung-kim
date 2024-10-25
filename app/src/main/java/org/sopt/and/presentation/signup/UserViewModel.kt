package org.sopt.and.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.R

class UserViewModel : ViewModel() {
    private var userId: String? = null
    private var userPassword: String? = null

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
                Log.d("userId",userId.toString())
                userPassword = password
                onResult(true, 0)
            }
        }
    }

    // 로그인 로직
    fun logIn(id: String, password: String) {
        if (id == userId && password == userPassword) {
            _loginResult.value = true  // 로그인 성공
        } else {
            _loginResult.value = false // 로그인 실패
        }
    }
}