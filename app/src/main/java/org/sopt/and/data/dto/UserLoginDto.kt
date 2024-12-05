package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUserLoginDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)

@Serializable
data class ResponseUserLoginDto(
    // success와 failed의 다른 필드를 정의하기 위해 null로 설정
    @SerialName("result")
    val result: ResponseLoginResultDto? = null,
    @SerialName("code")
    val code: String? = null
)

@Serializable
data class ResponseLoginResultDto(
    @SerialName("token")
    val token: String
)