package org.sopt.and.data.service

import org.sopt.and.data.dto.RequestUserRegisterDto
import org.sopt.and.data.dto.ResponseUserRegisterDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun getSingleUser(
        @Body request: RequestUserRegisterDto
    ): Call<ResponseUserRegisterDto>
}