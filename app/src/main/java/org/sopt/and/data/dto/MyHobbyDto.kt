package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyHobbyDto(
    // success와 failed의 다른 필드를 정의하기 위해 null로 설정
    @SerialName("result")
    val result: ResponseHobbyResultDto? = null,
    @SerialName("code")
    val code: String? = null
)

@Serializable
data class ResponseHobbyResultDto(
    @SerialName("hobby")
    val hobby: String
)