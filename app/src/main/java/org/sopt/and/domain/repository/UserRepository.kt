package org.sopt.and.domain.repository

import org.sopt.and.domain.model.MyHobbyResponse
import org.sopt.and.domain.model.UserLoginRequest
import org.sopt.and.domain.model.UserLoginResponse
import org.sopt.and.domain.model.UserRegisterRequest
import org.sopt.and.domain.model.UserRegisterResponse

interface UserRepository {
    suspend fun postUserRegistering(
        userRegisterRequest: UserRegisterRequest
    ): Result<UserRegisterResponse>

    suspend fun postUserLogin(
        userLoginRequest: UserLoginRequest
    ): Result<UserLoginResponse>

    suspend fun getUserHobby(): Result<MyHobbyResponse>
}