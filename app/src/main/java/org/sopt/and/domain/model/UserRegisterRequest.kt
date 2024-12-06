package org.sopt.and.domain.model

data class UserRegisterRequest(
    val username: String,
    val password: String,
    val hobby: String
)