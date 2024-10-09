package org.sopt.and.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray2
import org.sopt.and.ui.theme.Typography

@Composable
fun MyScreen(navController: NavController) {
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
                text = "이름 받을 곳",
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

        Text(
            text = "첫 결제시 첫 달 100원!",
            color = Color.Gray,
            style = Typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray2)
                .padding(16.dp, top = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray2)
                .padding(start = 16.dp, bottom = 16.dp)
        ) {
            Text("구매하기",
                style = Typography.titleSmall,
                color = Color.White)
            Image(
                painter = painterResource(R.drawable.ic_after),
                contentDescription = "구매하기 버튼")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp))

        Text(
            text = "현재 보유하신 이용권이 없습니다.",
            color = Color.Gray,
            style = Typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray2)
                .padding(start = 16.dp, top = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray2)
                .padding(start = 16.dp, bottom = 16.dp)
        ) {
            Text(text = "구매하기",
                style = Typography.titleSmall,
                color = Color.White)
            Image(
                painter = painterResource(R.drawable.ic_after),
                contentDescription = "구매하기 버튼")
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "전체 시청내역",
                color = Color.White,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_warning),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
                alignment = Alignment.Center,
                contentDescription = "시청 내역 없음"
            )
            Text(text = "시청내역이 없어요",
                color = Color.Gray,
                style = Typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "관심 프로그램",
                color = Color.White,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_warning),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
                contentDescription = "시청 내역 없음"
            )
            Text(text = "관심 프로그램이 없어요",
                color = Color.Gray,
                style = Typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }
    }
}