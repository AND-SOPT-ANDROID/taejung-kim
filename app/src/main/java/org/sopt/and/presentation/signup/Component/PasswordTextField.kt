package org.sopt.and.presentation.signup.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun PasswordField(
    passwordState: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    val showPassword = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        placeholder = { Text(stringResource(R.string.log_in_passwd)) },
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val text = if (showPassword.value) "hide" else "show"
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { showPassword.value = !showPassword.value }
            )
        }
    )
}