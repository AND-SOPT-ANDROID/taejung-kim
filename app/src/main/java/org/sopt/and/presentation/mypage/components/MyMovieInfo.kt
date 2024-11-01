package org.sopt.and.presentation.mypage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.Typography

@Composable
fun MyMovieInfo(
    titleResId: Int,
    imageResId: Int,
    noContentResId: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = titleResId),
            color = Color.White,
            style = Typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            painter = painterResource(id = imageResId),
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp),
            alignment = Alignment.Center,
            contentDescription = stringResource(id = noContentResId)
        )
        Text(
            text = stringResource(id = noContentResId),
            color = Color.Gray,
            style = Typography.titleSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
