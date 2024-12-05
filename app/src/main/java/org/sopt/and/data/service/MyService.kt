package org.sopt.and.data.service

import org.sopt.and.data.dto.ResponseMyHobbyDto
import retrofit2.Call
import retrofit2.http.GET

// TokenInterceptor 에서 Header intercept
interface MyService {
    @GET("/user/my-hobby")
    fun getMyHobby(
    ): Call<ResponseMyHobbyDto>
}