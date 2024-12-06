package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.base.BaseResponse
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegisterRequestDto
import org.sopt.and.data.remote.model.response.MyHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserLoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegisterResponseDto
import org.sopt.and.domain.SharedPreferenceManager
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun postUserRegister(
        @Body request: UserRegisterRequestDto
    ): BaseResponse<UserRegisterResponseDto>

    @POST("/login")
    suspend fun postUserLogin(
        @Body request: UserLoginRequestDto
    ): BaseResponse<UserLoginResponseDto>

    @GET("/user/my-hobby")
    suspend fun getMyHobby(
    ): BaseResponse<MyHobbyResponseDto>
}