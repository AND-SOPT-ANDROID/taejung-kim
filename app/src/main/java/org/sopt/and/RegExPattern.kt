package org.sopt.and

// 정규표현식 객체 생성
object RegExPattern {
    val idPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    val passwdPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$".toRegex()
}
