package org.sopt.and.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.presentation.home.MovieData
import org.sopt.and.ui.theme.Typography

@Composable
fun MovieTopItem(movieData: MovieData) {
    Box {
        // corner radius 설정
        Card(
            shape = RoundedCornerShape(5.dp),
        ) {
            Image(
                painter = painterResource(movieData.img),
                contentDescription = "영화 이미지",
                modifier = Modifier
                    .width(240.dp)
                    .aspectRatio(0.7f),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(y = 40.dp)
                .padding(start = 10.dp),
            text = movieData.title,
            style = Typography.titleLarge,
            fontSize = 80.sp,
            color = Color.White
        )
    }
}