package org.sopt.and

// 바텀 네비게이션 뷰에 필요한 데이터 객체 정의
data class BottomNavigationItem(
    val label: Int,
    val icon: Int,
    val route: String
)