package org.sopt.and

import org.sopt.and.RegExPattern.idPattern
import org.sopt.and.RegExPattern.passwdPattern

// 아이디 비밀번호 검증하는 함수 생성
fun checkSignUpValue(id: String, passwd: String): String {
    // 앞뒤 공백 제거
    val trimId = id.trim()
    val trimPasswd = passwd.trim()

    // 아이디 패턴과 매칭
    if (!trimId.matches(idPattern)) {
        return "idError"
    }
    // 비밀번호 패턴과 매칭
    if (!trimPasswd.matches(passwdPattern)) {
        return "passwdError"
    }

    return "correct"
}