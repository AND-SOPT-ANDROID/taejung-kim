package org.sopt.and.presentation.login.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.and.R

@Composable
fun AuthManagement(
    navController: NavController,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        LoginSectionText(
            text = stringResource(R.string.log_in_find_id),
            onClick = {
                // 아이디 찾기
            }
        )

        VerticalDivider(
            color = Color.Gray,
            modifier = Modifier
                .height(12.dp)
                .width(1.dp)
        )

        LoginSectionText (
            text = stringResource(R.string.log_in_passwd_change),
            onClick = {
                // 비밀번호 재설정
            }
        )

        VerticalDivider(
            color = Color.Gray,
            modifier = Modifier
                .height(12.dp)
                .width(1.dp)
        )

        LoginSectionText(
            text = stringResource(R.string.log_in_sign_up),
            onClick = { navController.navigate("signup") }
        )
    }
}

@Composable
fun LoginSectionText(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick),
        color = Color.Gray
    )
}
