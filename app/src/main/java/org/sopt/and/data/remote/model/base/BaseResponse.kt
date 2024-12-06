package org.sopt.and.data.remote.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val code: Int,
    val result: T?
)