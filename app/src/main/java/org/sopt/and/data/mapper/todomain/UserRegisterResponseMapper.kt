package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.UserRegisterResponseDto
import org.sopt.and.domain.model.UserRegisterResponse

fun UserRegisterResponseDto.toDomain(): UserRegisterResponse = UserRegisterResponse(
    no = no
)