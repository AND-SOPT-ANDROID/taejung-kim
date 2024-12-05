package org.sopt.and.data.service

import org.sopt.and.data.dto.RequestUserLoginDto
import org.sopt.and.data.dto.RequestUserRegisterDto
import org.sopt.and.data.dto.ResponseUserLoginDto
import org.sopt.and.data.dto.ResponseUserRegisterDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun postUserRegister(
        @Body request: RequestUserRegisterDto
    ): Call<ResponseUserRegisterDto>

    @POST("/login")
    fun postUserLogin(
        @Body request: RequestUserLoginDto
    ): Call<ResponseUserLoginDto>
}