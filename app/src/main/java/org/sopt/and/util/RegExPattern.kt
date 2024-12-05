package org.sopt.and.util

// 정규표현식 객체 생성
object RegExPattern {
    // 8자 이상이면 오류
    private val maxLengthPattern = "^.{0,7}$".toRegex()

    fun validateMaxLength(input: String): Boolean {
        return input.trim().matches(maxLengthPattern)
    }
}