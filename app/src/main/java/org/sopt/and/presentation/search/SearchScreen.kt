package org.sopt.and.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SearchScreen(paddingValues: PaddingValues) {
    Text(
        text = "검색 화면",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        color = Color.White
    )
}