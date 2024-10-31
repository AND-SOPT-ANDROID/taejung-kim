package org.sopt.and.presentation.signup.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import org.sopt.and.R

@Composable
fun IdTextField(
    valueState: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier,
    isSingleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        singleLine = isSingleLine,
        placeholder = { Text(stringResource(R.string.log_in_id)) },
        textStyle = TextStyle(color = Color.White),
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions
    )
}