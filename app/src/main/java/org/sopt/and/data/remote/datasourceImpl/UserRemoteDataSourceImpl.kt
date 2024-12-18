package org.sopt.and.data.remote.datasourceImpl

import android.util.Log
import org.sopt.and.data.remote.datasource.UserRemoteDataSource
import org.sopt.and.data.remote.model.base.BaseResponse
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegisterRequestDto
import org.sopt.and.data.remote.model.response.MyHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserLoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegisterResponseDto
import org.sopt.and.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun postUserRegistering(userRegisterRequestDto: UserRegisterRequestDto): BaseResponse<UserRegisterResponseDto> {
        return try {
            userService.postUserRegister(userRegisterRequestDto)
        } catch (e: Exception) {
            Log.e("NetworkError", e.message ?: "Unknown error")
            throw e
        }
    }

    override suspend fun postUserLogin(userLoginRequestDto: UserLoginRequestDto) =
        userService.postUserLogin(userLoginRequestDto)


    override suspend fun getUserHobby(): BaseResponse<MyHobbyResponseDto> =
        userService.getMyHobby()

}