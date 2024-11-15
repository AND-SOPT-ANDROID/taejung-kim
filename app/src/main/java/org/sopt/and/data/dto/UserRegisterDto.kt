package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUserRegisterDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("hobby")
    val hobby: String
)

@Serializable
data class ResponseUserRegisterDto(
    @SerialName("result")
    val result: ResponseRegisterResultDto? = null,
    @SerialName("code")
    val code : String? = null
)

@Serializable
data class ResponseRegisterResultDto(
    @SerialName("no")
    val no: Int
)