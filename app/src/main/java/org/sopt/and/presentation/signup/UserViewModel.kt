package org.sopt.and.presentation.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.ServicePool
import org.sopt.and.data.dto.RequestUserLoginDto
import org.sopt.and.data.dto.RequestUserRegisterDto
import org.sopt.and.data.dto.ResponseUserLoginDto
import org.sopt.and.data.dto.ResponseUserRegisterDto
import org.sopt.and.domain.SharedPreferenceManager
import org.sopt.and.domain.SharedPreferenceManager.saveToken
import org.sopt.and.domain.SharedPreferenceManager.saveUserName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

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

    // 유저 정보를 관리
    private val _userState = mutableStateOf<ResponseUserLoginDto?>(null)
    val userState: State<ResponseUserLoginDto?> get() = _userState


    // 회원가입 로직
    fun signUp(username: String, password: String, hobby: String) {
        viewModelScope.launch {
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
                    val error = response.message()
                    Log.e("error", error.toString())
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
                        _userState.value = loginResponse
                        loginResponse?.result?.token?.let {
                            // SharedPreference에 토큰 및 유저네임 저장
                            saveToken(it)
                            saveUserName(username)
                        }

                        Log.d("token?", _userState.value.toString())
                        _authState.value = AuthState.Success(AuthType.LOGIN)
                    } else {
                        val error = response.message()
                        Log.e("error", error.toString())
                    }
                }

                override fun onFailure(call: Call<ResponseUserLoginDto>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }

            })
        }
    }
}