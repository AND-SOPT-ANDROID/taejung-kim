package org.sopt.and.presentation.signup

import org.sopt.and.util.RegExPattern

// 아이디 비밀번호 , 취미 검증하는 함수 생성
fun checkSignUpValue(id: String, passwd: String, hobby: String): String {
    if (!RegExPattern.validateMaxLength(id)) {
        return "idError"
    }
    if (!RegExPattern.validateMaxLength(passwd)) {
        return "passwdError"
    }
    if (!RegExPattern.validateMaxLength(hobby)) {
        return "hobbyError"
    }
    return "correct"
}