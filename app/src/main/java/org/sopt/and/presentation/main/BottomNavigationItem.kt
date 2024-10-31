package org.sopt.and.presentation.main

import androidx.compose.ui.graphics.Color
import org.sopt.and.R
import org.sopt.and.presentation.navigation.Screen

// 바텀 네비게이션 뷰에 필요한 데이터 객체 정의
sealed class BottomNavItem(
    val route: String,
    val iconResource: Int,
    val label: String,
    val selectedColor: Color,
    val unselectedColor: Color
) {
    // route를 Screen route와 일치시키게 변경
    object Home : BottomNavItem(Screen.Home.route, R.drawable.ic_home, "Home", Color.White, Color.Gray)
    object Search : BottomNavItem(Screen.Search.route, R.drawable.ic_search, "Search", Color.White, Color.Gray)
    object My : BottomNavItem(Screen.MyPage.route, R.drawable.ic_my, "My", Color.White, Color.Gray)
}
