package org.sopt.and.presentation.navigation

sealed class Screen(val route: String) {
    object LogIn : Screen("login")
    object SignUp : Screen("signup")
    object MainScreen : Screen("mainScreen")
    object Home : Screen("home")
    object Search : Screen("search")
    object MyPage : Screen("myPage")
}
