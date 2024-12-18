package org.sopt.and.domain.model

data class UserLoginRequest(
    val username: String,
    val password: String,
)