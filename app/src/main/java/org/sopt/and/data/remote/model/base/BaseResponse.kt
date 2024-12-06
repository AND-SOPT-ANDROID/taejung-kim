package org.sopt.and.data.remote.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    // code를 반환하는 경우는, 에러타입이므로 반환하지 않는 경우는 200 처리를 했습니다.
    val code: Int = 200,
    val result: T?
)