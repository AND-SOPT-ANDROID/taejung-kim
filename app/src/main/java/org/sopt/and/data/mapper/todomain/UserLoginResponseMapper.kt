package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.UserLoginResponseDto
import org.sopt.and.domain.model.UserLoginResponse

fun UserLoginResponseDto.toDomain(): UserLoginResponse = UserLoginResponse(
    token = token
)