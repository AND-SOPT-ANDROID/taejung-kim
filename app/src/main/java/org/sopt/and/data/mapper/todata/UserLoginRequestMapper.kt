package org.sopt.and.data.mapper.todata

import org.sopt.and.data.remote.model.request.UserLoginRequestDto
import org.sopt.and.domain.model.UserLoginRequest

fun UserLoginRequest.toData(): UserLoginRequestDto = UserLoginRequestDto(
    username = username,
    password = password
)