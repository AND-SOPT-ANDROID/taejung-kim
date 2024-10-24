package org.sopt.and.screens

import android.util.Log
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.MainViewModel
import org.sopt.and.R
import org.sopt.and.ui.theme.Gray2
import org.sopt.and.ui.theme.Typography

@Composable
fun MyScreen(navController: NavController, mainViewModel: MainViewModel) {

    // ViewModel에서 LiveData를 관찰
    // runtime-livedata 라이브러리를 이용
    val id = mainViewModel.id.observeAsState("")

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
                text = "${id.value}",
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
            text = stringResource(R.string.my_first),
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
            Text(
                stringResource(R.string.my_purchase),
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
            text = stringResource(R.string.my_no_purchase),
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
            Text(text = stringResource(R.string.my_purchase),
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
                text = stringResource(R.string.my_watching),
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
            Text(text = stringResource(R.string.my_no_watching),
                color = Color.Gray,
                style = Typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.my_wish),
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
            Text(text = stringResource(R.string.my_no_wish),
                color = Color.Gray,
                style = Typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }
    }
}