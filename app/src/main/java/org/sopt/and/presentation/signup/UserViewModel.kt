package org.sopt.and.presentation.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.ServicePool
import org.sopt.and.data.dto.RequestUserLoginDto
import org.sopt.and.data.dto.RequestUserRegisterDto
import org.sopt.and.data.dto.ResponseUserLoginDto
import org.sopt.and.data.dto.ResponseUserRegisterDto
import org.sopt.and.domain.SharedPreferenceManager.saveToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface AuthState {
    class Success(val type: AuthType) : AuthState
    class Error(val messageResId: Int, val type: AuthType) : AuthState
}

enum class AuthType {
    LOGIN, SIGNUP
}

class UserViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    // 로그인 상태를 관리하기 위한 LiveData
    private val _authState = MutableStateFlow<AuthState?>(null)  // 초기 상태로 null 설정
    val authState: StateFlow<AuthState?> = _authState.asStateFlow()

    // 회원가입 로직
    fun signUp(username: String, password: String, hobby: String) {
        viewModelScope.launch {
            // 호출 전 8자리 검증 진행
            when (checkSignUpValue(username, password, hobby)) {
                "idError" -> _authState.value = AuthState.Error(R.string.sign_up_error, AuthType.SIGNUP)
                "passwdError" -> _authState.value = AuthState.Error(R.string.sign_up_paswd, AuthType.SIGNUP)
                "hobbyError" -> _authState.value = AuthState.Error(R.string.sign_up_error_hobby, AuthType.SIGNUP)
                else -> {
                    postUserRegister(RequestUserRegisterDto(username, password, hobby))
                }
            }
        }
    }

    fun postUserRegister(request : RequestUserRegisterDto){
        userService.postUserRegister(request).enqueue(object :
            Callback<ResponseUserRegisterDto>{
            override fun onResponse(call: Call<ResponseUserRegisterDto>, response: Response<ResponseUserRegisterDto>) {
                if (response.isSuccessful) {
                    _authState.value = AuthState.Success(AuthType.SIGNUP)
                } else {
                    _authState.value = AuthState.Error(R.string.sign_up_duplicate_name, AuthType.SIGNUP)
                }
            }

            override fun onFailure(call: Call<ResponseUserRegisterDto>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
            })

    }

    // 로그인 로직
    fun logIn(username: String, password: String) {
        viewModelScope.launch {
            userService.postUserLogin(RequestUserLoginDto(username, password)).enqueue(object :
                Callback<ResponseUserLoginDto>{
                override fun onResponse(call: Call<ResponseUserLoginDto>, response: Response<ResponseUserLoginDto>) {
                    if(response.isSuccessful) {
                        val loginResponse = response.body()
                        loginResponse?.result?.token?.let {
                            // SharedPreference에 토큰 저장
                            saveToken(it)
                        }
                        _authState.value = AuthState.Success(AuthType.LOGIN)
                    } else {
                        _authState.value = AuthState.Error(R.string.log_in_error, AuthType.LOGIN)
                    }
                }

                override fun onFailure(call: Call<ResponseUserLoginDto>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }

            })
        }
    }
}