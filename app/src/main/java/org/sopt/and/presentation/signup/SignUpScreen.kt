package org.sopt.and.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.presentation.signup.components.IdHobbyTextField
import org.sopt.and.presentation.signup.components.PasswordField
import org.sopt.and.presentation.signup.components.SignUpInfoRow
import org.sopt.and.presentation.signup.components.SignUpTitle
import org.sopt.and.presentation.signup.components.SocialServiceLogIn
import org.sopt.and.util.showToast

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    // textStyle 변경을 위한 textFieldValue 추적
    val idState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val hobbyState = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
    // 모든 textFiled가 채워졌는지 판단하는 변수
    val allFieldFilled = idState.value.text.isNotEmpty() && passwordState.value.text.isNotEmpty() && hobbyState.value.text.isNotEmpty()
    val registerState = viewModel.userRegisterState.collectAsStateWithLifecycle().value


    LaunchedEffect(registerState) {
        when (registerState) {
            is RegisterState.Loading -> { }
            is RegisterState.Success -> {
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            }

            is RegisterState.Failure -> {
                when (registerState.code) {
                    "00" -> context.showToast(R.string.sign_up_error)
                    "01" -> context.showToast(R.string.sign_up_eight)
                    else -> {}
                }
            }

            else -> {}
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
            SignUpTitle(
                firstText = stringResource(R.string.sign_up_title_top_start),
                firstColor = Color.White,
                secondText = stringResource(R.string.sign_up_title_top_end),
                secondColor = Color.Gray
            )

            SignUpTitle(
                firstText = stringResource(R.string.sign_up_title_bottom_start),
                firstColor = Color.White,
                secondText = stringResource(R.string.sign_up_title_bottom_end),
                secondColor = Color.Gray
            )
            Spacer(modifier = Modifier.weight(2f))
            IdHobbyTextField(
                valueState = idState,
                holderText = R.string.log_in_id,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.weight(0.5f))
            SignUpInfoRow(
                iconResId = R.drawable.ic_info,
                text = stringResource(R.string.sign_up_id)
            )

            Spacer(modifier = Modifier.weight(1f))
            PasswordField(
                passwordState = passwordState,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.weight(0.5f))
            SignUpInfoRow(
                iconResId = R.drawable.ic_info,
                text = stringResource(R.string.sign_up_passwd)
            )

            Spacer(modifier = Modifier.weight(1f))
            IdHobbyTextField(
                valueState = hobbyState,
                holderText = R.string.sign_up_hobby,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.weight(2f))
            SocialServiceLogIn()
            Spacer(modifier = Modifier.weight(8f))
        }

        Text(
            text = "Wavve 회원가입",
            color = Color.White,
            modifier = Modifier
                .background(
                    if (allFieldFilled) Color.Blue else Color.Gray,
                )
                .padding(10.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clickable(
                    enabled = allFieldFilled,
                    onClick = {
                        val textId = idState.value.text
                        val textPasswd = passwordState.value.text
                        val textHobby = hobbyState.value.text
                        // viewModel의 signUp을 통해 success boolean 판단
                        viewModel.signUp(textId, textPasswd, textHobby)
                    }
                ),
            textAlign = TextAlign.Center
        )

    }
}