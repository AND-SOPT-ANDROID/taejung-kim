package org.sopt.and.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun MyScreen(navController: NavController) {
    Text(
        text = "마이페이지 화면",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        color = Color.White
    )
}