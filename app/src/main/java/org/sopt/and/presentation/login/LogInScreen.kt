package org.sopt.and.presentation.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.main.MainActivity
import org.sopt.and.presentation.signup.UserViewModel

@Composable
fun LogInScreen(
    navController: NavController,
    viewModel: UserViewModel

) {
    val loginResult by viewModel.loginResult.observeAsState()
    val context = LocalContext.current as Activity
    val snackbarHostState = remember { SnackbarHostState() }

    // SnackBar 구현을 위해 Scaffold 안에 정의
    Scaffold(
        // 스낵바의 표시 상태 관리
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = "Wavve",
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(R.drawable.ic_before),
                    contentDescription = "X 버튼",
                    modifier = Modifier.size(28.dp)
                )
            }

            // id text remeber를 통한 변수 변경
            var textId by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.weight(2f))
            // 윤곽선의 색상 및 두께를 커스텀 가능한 OutLinedTextField
            OutlinedTextField(
                value = textId,
                textStyle = TextStyle(Color.White),
                onValueChange = { textId = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.log_in_id)) },
                singleLine = true,
            )


            Spacer(modifier = Modifier.height(10.dp))
            // password text remeber를 통한 변수 변경
            var textPasswd by remember { mutableStateOf("") }
            // password boolean remeber를 통한 숨김 UI 변경
            var passwdVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = textPasswd,
                textStyle = TextStyle(Color.White),
                onValueChange = { textPasswd = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.log_in_passwd)) },
                singleLine = true,
                // passwdVisible boolean에 따라 표시가 다르게
                visualTransformation = if (passwdVisible) VisualTransformation.None else PasswordVisualTransformation(),
                // show, hide 표시
                trailingIcon = {
                    val text = if (passwdVisible) "hide" else "show"
                    Text(text = text,
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable {
                                passwdVisible = !passwdVisible
                            })
                }

            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {viewModel.logIn(textId, textPasswd)},
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                // ButtonDefaults 의 4가지 형태
                colors = ButtonDefaults.buttonColors(
                    // container 색
                    containerColor = Color.Blue,
                    // 버튼 안의 content 색
                    contentColor = Color.White,
                    // 비활성화 시 container 색
                    disabledContainerColor = Color.Blue,
                    // 비활성화 시 content 색
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.log_in_execute))
            }

            // viewModel의 loginResult을 옵저빙하여 로그인 이동
            LaunchedEffect(loginResult) {
                loginResult?.let {
                    if (it) {
                        navController.navigate("mainScreen") {
                            // navigation stack에서 이전 화면 제거
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        // 스낵바 간소화
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.log_in_error),
                            actionLabel = context.getString(R.string.log_in_ok)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    stringResource(R.string.log_in_find_id),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Gray
                )

                VerticalDivider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(12.dp)
                        .width(1.dp)
                )

                Text(
                    text = stringResource(R.string.log_in_passwd_change),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Gray
                )

                VerticalDivider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(12.dp)
                        .width(1.dp)
                )

                Text(
                    text = stringResource(R.string.log_in_sign_up),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable(
                            enabled = true,
                            onClick = {navController.navigate("signup")}
                        ),
                    color = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.weight(1.5f))
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

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(4f))
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.ic_kakao),
                    contentDescription = "카카오 로고"
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.ic_face_book),
                    contentDescription = "페이스북 로고"
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.ic_github),
                    contentDescription = "깃허브 로고"
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.ic_discord),
                    contentDescription = "디스코드 로고"
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.ic_kakao),
                    contentDescription = "카카오 로고"
                )
                Spacer(modifier = Modifier.weight(4f))
            }

            Spacer(modifier = Modifier.weight(1f))
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
            Spacer(modifier = Modifier.weight(4f))
        }
    }

}