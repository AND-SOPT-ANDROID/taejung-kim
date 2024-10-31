package org.sopt.and.presentation.signup

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.presentation.signup.Component.IdTextField
import org.sopt.and.presentation.signup.Component.PasswordField

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: UserViewModel
) {
    // textStyle 변경을 위한 textFieldValue 추적
    val idState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    // id text remeber를 통한 변수 변경
    var textId by remember { mutableStateOf("") }
    // password text remeber를 통한 변수 변경
    var textPasswd by remember { mutableStateOf("") }
    val lifecycleOwner = LocalLifecycleOwner.current
    val signUpSate by viewModel.signUpState.collectAsStateWithLifecycle(lifecycleOwner)
    val context = LocalContext.current

    LaunchedEffect(signUpSate) {
        when (signUpSate) {
            is UserViewModel.SignUpState.Success -> {
                navController.navigate("login")
            }
            is UserViewModel.SignUpState.Error -> {
                Toast.makeText(context, context.getString((signUpSate as UserViewModel.SignUpState.Error).messageResId), Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        // column 과 Box 구분
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 44.dp)
                .background(Color.Black)
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = "회원가입",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(R.drawable.ic_exit),
                    contentDescription = "X 버튼",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.weight(3f))
            Row {
                Text(
                    text = "이메일과 비밀번호",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "만으로",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }

            Row {
                Text(
                    text = "Wavve를 즐길 수 ",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "있어요!",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.weight(2f))
            IdTextField(
                valueState = idState,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.weight(0.5f))
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_info),
                    contentDescription = "info",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = stringResource(R.string.sign_up_id),
                    modifier = Modifier.weight(1f),
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            PasswordField(
                passwordState = passwordState,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.weight(0.5f))
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_info),
                    contentDescription = "info",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = stringResource(R.string.sign_up_passwd),
                    modifier = Modifier.weight(1f),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.weight(2f))
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

        Text(
            text = "Wavve 회원가입",
            color = Color.White,
            modifier = Modifier
                .background(Color.Gray)
                .padding(10.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clickable(
                    enabled = true,
                    onClick = {
                        textId = idState.value.text
                        textPasswd = passwordState.value.text
                        // viewModel의 signUp을 통해 success boolean 판단
                        viewModel.signUp(textId, textPasswd)
                    }
                ),
            textAlign = TextAlign.Center
        )

    }
}