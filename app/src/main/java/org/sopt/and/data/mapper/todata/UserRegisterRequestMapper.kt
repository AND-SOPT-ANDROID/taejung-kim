package org.sopt.and.data.mapper.todata

import org.sopt.and.data.remote.model.request.UserRegisterRequestDto
import org.sopt.and.domain.model.UserRegisterRequest

fun UserRegisterRequest.toData(): UserRegisterRequestDto = UserRegisterRequestDto(
    username = username,
    password = password,
    hobby = hobby
)