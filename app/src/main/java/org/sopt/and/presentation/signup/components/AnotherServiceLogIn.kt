package org.sopt.and.presentation.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun AnotherServiceLogIn() {
    AnotherTopLine()
    Spacer(modifier = Modifier.padding(10.dp))
    SocialIconLine()
    Spacer(modifier = Modifier.padding(10.dp))
    AnotherBottomLine()
}

@Composable
fun AnotherTopLine(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // 실선을 위해 좌우 Spacer 배치
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Color.Gray)
                .weight(1f)
        )
        Text(
            "또는 다른 서비스 계정으로 가입",
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color.Gray
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Color.Gray)
                .weight(1f)
        )
    }
}

@Composable
fun SocialIconLine(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(4f))
        SocialIcon(iconRes = R.drawable.ic_kakao, contentDescription = "카카오 로고")
        Spacer(modifier = Modifier.weight(1f))
        SocialIcon(iconRes = R.drawable.ic_face_book, contentDescription = "페이스북 로고")
        Spacer(modifier = Modifier.weight(1f))
        SocialIcon(iconRes = R.drawable.ic_github, contentDescription = "깃허브 로고")
        Spacer(modifier = Modifier.weight(1f))
        SocialIcon(iconRes = R.drawable.ic_discord, contentDescription = "디스코드 로고")
        Spacer(modifier = Modifier.weight(1f))
        SocialIcon(iconRes = R.drawable.ic_kakao, contentDescription = "카카오 로고")
        Spacer(modifier = Modifier.weight(4f))
    }
}

@Composable
fun SocialIcon(
    iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit = {}
) {
    Image(
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick), // 추후 SNS 클릭 고려 설정
        painter = painterResource(id = iconRes),
        contentDescription = contentDescription
    )
}

@Composable
fun AnotherBottomLine(){
    Row {
        Text(
            modifier = Modifier.padding(end = 4.dp),
            text = "-",
            color = Color.Gray,
            fontSize = 12.sp
        )
        Text(
            text = stringResource(R.string.sns_pooq_wavve),
            modifier = Modifier.weight(1f),
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

