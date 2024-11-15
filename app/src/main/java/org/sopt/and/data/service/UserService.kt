package org.sopt.and.data.service

import org.sopt.and.data.dto.ResponseUserRegisterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/api/users/{userId}")
    fun getSingleUser(
        @Path("userId") userId: Int
    ): Call<ResponseUserRegisterDto>
}