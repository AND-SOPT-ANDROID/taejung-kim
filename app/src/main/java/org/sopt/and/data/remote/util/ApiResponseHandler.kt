package org.sopt.and.data.remote.util

import org.sopt.and.data.remote.model.base.BaseResponse

fun <T> BaseResponse<T>.handleBaseResponse(): Result<T> {
    return when (this.code) {
        in 200..299 -> {
            if (this.result != null) {
                Result.success(this.result)
            } else {
                Result.failure(Exception("No data available"))
            }
        }
        in 300..399 -> {
            Result.failure(Exception("Redirection error"))
        }
        in 400..499 -> {
            Result.failure(Exception("Client error"))
        }
        in 500..599 -> {
            Result.failure(Exception("Server error"))
        }
        else -> {
            Result.failure(Exception("Unexpected error"))
        }
    }
}