package org.sopt.and.data.remote.datasource

import org.sopt.and.data.remote.model.base.BaseResponse
import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.data.remote.model.request.UserRegisterRequestDto
import org.sopt.and.data.remote.model.response.MyHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserLoginResponseDto
import org.sopt.and.data.remote.model.response.UserRegisterResponseDto

interface UserRemoteDataSource {
    suspend fun postUserRegistering(userRegisterRequestDto: UserRegisterRequestDto): BaseResponse<UserRegisterResponseDto>
    suspend fun postUserLogin(userLoginRequestDto: UserLoginRequestDto): BaseResponse<UserLoginResponseDto>
    suspend fun getUserHobby(): BaseResponse<MyHobbyResponseDto>
}