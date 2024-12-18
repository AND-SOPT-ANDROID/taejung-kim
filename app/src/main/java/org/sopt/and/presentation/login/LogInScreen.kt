package org.sopt.and.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.presentation.login.components.AuthManagement
import org.sopt.and.presentation.signup.SignUpEvent
import org.sopt.and.presentation.signup.components.PasswordField
import org.sopt.and.presentation.signup.components.IdHobbyTextField
import org.sopt.and.presentation.signup.components.SocialServiceLogIn
import org.sopt.and.util.showSnackBar

@Composable
fun LogInScreen(
    navController: NavController,
    viewModel: LogInViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is LogInEffect.ShowSnackBar -> {
                    snackBarHostState.showSnackBar(
                        context = context,
                        scope = coroutineScope,
                        message = effect.message
                    )
                }
                is LogInEffect.NavigateToMain -> {
                    navController.popBackStack("login", inclusive = true)
                    navController.navigate("mainScreen")
                }
            }
        }
    }

    // SnackBar 구현을 위해 Scaffold 안에 정의
    Scaffold(
        // 스낵바의 표시 상태 관리
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart
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
            Spacer(modifier = Modifier.weight(2f))
            // 윤곽선의 색상 및 두께를 커스텀 가능한 OutLinedTextField
            IdHobbyTextField(
                valueState = state.username,
                onValueChange = {
                    viewModel.setEvent(LogInEvent.UsernameChanged(it))
                },
                holderText = R.string.log_in_id,
                modifier = Modifier.padding(bottom = 8.dp)
            )


            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(
                passwordState = state.password,
                onValueChange = {
                    viewModel.setEvent(LogInEvent.PasswordChanged(it))
                },
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.setEvent(LogInEvent.LogInClicked)
                },
                modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp),
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

            Spacer(modifier = Modifier.weight(1f))
            // 아이디 찾기, 비밀번호, 회원가입 컴포넌트
            AuthManagement(navController = navController)
            Spacer(modifier = Modifier.weight(1f))
            // 또는 다른 서비스 계정 컴포넌트
            SocialServiceLogIn()
            Spacer(modifier = Modifier.weight(7f))
        }
    }

}