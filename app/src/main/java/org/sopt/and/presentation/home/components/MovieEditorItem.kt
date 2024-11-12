package org.sopt.and.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.presentation.home.MovieData

@Composable
fun MovieEditorItem(movieData: MovieData) {
    Card(
        shape = RoundedCornerShape(5.dp),
    ) {
        Image(
            painter = painterResource( movieData.img),
            contentDescription = "영화 이미지",
            modifier = Modifier
                .width(120.dp)
                .aspectRatio(0.7f),
            contentScale = ContentScale.Crop
        )
    }
}