package org.sopt.and.data.repositoryImpl

import org.sopt.and.data.mapper.todata.toData
import org.sopt.and.data.mapper.todomain.toDomain
import org.sopt.and.data.remote.datasource.UserRemoteDataSource
import org.sopt.and.data.remote.util.handleBaseResponse
import org.sopt.and.domain.model.MyHobbyResponse
import org.sopt.and.domain.model.UserLoginRequest
import org.sopt.and.domain.model.UserLoginResponse
import org.sopt.and.domain.model.UserRegisterRequest
import org.sopt.and.domain.model.UserRegisterResponse
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun postUserRegistering(
        userRegisterRequest: UserRegisterRequest
    ): Result<UserRegisterResponse> {
        return runCatching {
            userRemoteDataSource.postUserRegistering(
                userRegisterRequestDto = userRegisterRequest.toData()
            ).handleBaseResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun postUserLogin(userLoginRequest: UserLoginRequest): Result<UserLoginResponse> {
        return runCatching {
            userRemoteDataSource.postUserLogin(
                userLoginRequestDto = userLoginRequest.toData()
            ).handleBaseResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun getUserHobby(): Result<MyHobbyResponse> {
        return runCatching {
            userRemoteDataSource.getUserHobby().handleBaseResponse().getOrThrow().toDomain()
        }
    }
}