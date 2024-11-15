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
import org.sopt.and.data.dto.RequestUserRegisterDto
import org.sopt.and.data.dto.ResponseUserRegisterDto
import org.sopt.and.domain.SharedPreferenceManager
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
    private var userData = UserData()
    private val userService by lazy { ServicePool.userService }

    // 로그인 상태를 관리하기 위한 LiveData
    private val _authState = MutableStateFlow<AuthState?>(null)  // 초기 상태로 null 설정
    val authState: StateFlow<AuthState?> = _authState.asStateFlow()

    // 유저 정보를 관리
    private val _userState = mutableStateOf<ResponseUserRegisterDto?>(null)
    val userState: State<ResponseUserRegisterDto?> get() = _userState


    // 회원가입 로직
    fun signUp(id: String, password: String) {
        viewModelScope.launch {
            when (checkSignUpValue(id, password)) {
                "idError" -> _authState.value = AuthState.Error(R.string.sign_up_error, AuthType.SIGNUP)
                "passwdError" -> _authState.value = AuthState.Error(R.string.sign_up_paswd, AuthType.SIGNUP)
                else -> {
                    postUserRegister(RequestUserRegisterDto(id, password))
                    userData = UserData(id, password)
                }
            }
        }
    }

    fun postUserRegister(request : RequestUserRegisterDto){
        userService.postUserRegister(request).enqueue(object :
            Callback<ResponseUserRegisterDto>{
            override fun onResponse(call: Call<ResponseUserRegisterDto>, response: Response<ResponseUserRegisterDto>) {
                if (response.isSuccessful) {
                    _userState.value = response.body()
                    _authState.value = AuthState.Success(AuthType.SIGNUP)
                    userData = UserData(request.id, request.password)

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