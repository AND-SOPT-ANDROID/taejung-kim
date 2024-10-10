package org.sopt.and

// 아이디 비밀번호 검증하는 object 객체 생성
object CheckSignUp {
    // Boolean 값으로 반환
    fun checkSignUpValue(id: String, passwd: String): String {
        // 앞뒤 공백 제거
        val trimId = id.trim()
        val trimPasswd = passwd.trim()

        // (아이디)@(플랫폼).(주소번지) 구분
        val idPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        // 하나 이상 숫자, 하나 이상 대소문자, 하나 이상 특수문자, 공백 비허용, 비밀번호 길이 8이상 20이하
        val passwdPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$"

        // 정규표현식과 매칭
        if (!trimId.matches(idPattern.toRegex())) {
            return "idError"
        }
        // 정규표현식과 매칭
        if (!trimPasswd.matches(passwdPattern.toRegex())) {
            return "passwdError"
        }

        return "correct"
    }
}
