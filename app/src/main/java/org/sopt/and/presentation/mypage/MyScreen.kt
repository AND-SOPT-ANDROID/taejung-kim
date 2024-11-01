package org.sopt.and.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import org.sopt.and.presentation.main.MainViewModel
import org.sopt.and.R
import org.sopt.and.domain.SharedPreferenceManager
import org.sopt.and.presentation.mypage.components.MyMovieInfo
import org.sopt.and.presentation.mypage.components.MyPurchaseInfo
import org.sopt.and.ui.theme.Gray2
import org.sopt.and.ui.theme.Typography

@Composable
fun MyScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray2)
                .padding(16.dp, vertical = 24.dp),
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp),
                painter = painterResource(R.drawable.ic_kakao),
                contentDescription = "카카오 로고",
                alignment = Alignment.CenterStart,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "${SharedPreferenceManager.getUserId()}",
                style = Typography.titleSmall,
                color = Color.White,
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_alarm),
                    contentDescription = "카카오 로고",
                )
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = "카카오 로고",
                )
            }
        }

        MyPurchaseInfo(
            headerTextResId = R.string.my_no_purchase,
            buttonTextResId = R.string.my_first,
            iconResId = R.drawable.ic_after,
            contentResId = R.string.my_no_purchase
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp))

        MyPurchaseInfo(
            headerTextResId = R.string.my_no_purchase,
            buttonTextResId = R.string.my_purchase,
            iconResId = R.drawable.ic_after,
            contentResId = R.string.my_no_purchase
        )
        MyMovieInfo(
            titleResId = R.string.my_watching,
            imageResId = R.drawable.ic_warning,
            noContentResId = R.string.my_no_watching
        )

        MyMovieInfo(
            titleResId = R.string.my_wish,
            imageResId = R.drawable.ic_warning,
            noContentResId = R.string.my_no_wish
        )
    }
}