package org.sopt.and.presentation.mypage.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.Gray2
import org.sopt.and.ui.theme.Typography

@Composable
fun MyPurchaseInfo(
    headerTextResId: Int,
    buttonTextResId: Int,
    @DrawableRes iconResId: Int,
    contentResId: Int
) {
    Text(
        text = stringResource(id = headerTextResId),
        color = Color.Gray,
        style = Typography.titleSmall,
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray2)
            .padding(16.dp, top = 8.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray2)
            .padding(start = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = buttonTextResId),
            style = Typography.titleSmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = stringResource(contentResId),
            modifier = Modifier.size(24.dp)
        )
    }
}

