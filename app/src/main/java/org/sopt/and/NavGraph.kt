package org.sopt.and

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.screens.HomeScreen
import org.sopt.and.screens.MyScreen
import org.sopt.and.screens.SearchScreen

// navigation graph route에 따른 이동 정의
@Composable
fun NavGraph(navController: NavHostController, startDestination: String = "home") {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("search") {
            SearchScreen(navController)
        }
        composable("my") {
            MyScreen(navController)
        }
    }
}