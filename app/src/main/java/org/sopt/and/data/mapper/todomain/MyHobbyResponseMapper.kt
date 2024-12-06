package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.remote.model.response.MyHobbyResponseDto
import org.sopt.and.data.remote.model.response.UserLoginResponseDto
import org.sopt.and.domain.model.MyHobbyResponse
import org.sopt.and.domain.model.UserLoginResponse

fun MyHobbyResponseDto.toDomain(): MyHobbyResponse = MyHobbyResponse(
    hobby = hobby
)