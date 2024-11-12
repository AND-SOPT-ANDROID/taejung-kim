package org.sopt.and.presentation.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpInfoRow(iconResId: Int, text: String) {
    Row {
        Image(
            painter = painterResource(iconResId),
            contentDescription = "info",
            modifier = Modifier
                .size(24.dp)
                .padding(end = 4.dp)
        )
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            color = Color.Gray,
            fontSize = 12.sp,
        )
    }
}