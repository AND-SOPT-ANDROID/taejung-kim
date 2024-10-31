package org.sopt.and.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.login.LogInScreen
import org.sopt.and.presentation.navigation.Screen
import org.sopt.and.presentation.signup.SignUpScreen
import org.sopt.and.presentation.signup.UserViewModel
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val userViewModel: UserViewModel = viewModel()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LogIn.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.LogIn.route) {
                            LogInScreen(navController, userViewModel)
                        }
                        composable(Screen.SignUp.route) {
                            SignUpScreen(navController, userViewModel)
                        }
                        composable(Screen.MainScreen.route) {
                            MainScreen(navController)
                        }
                    }
                }
            }
        }
    }
}