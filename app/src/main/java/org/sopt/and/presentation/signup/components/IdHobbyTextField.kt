package org.sopt.and.presentation.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle

@Composable
fun IdHobbyTextField(
    valueState: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    holderText: Int,
    isSingleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = valueState,
        onValueChange = onValueChange,
        singleLine = isSingleLine,
        placeholder = { Text(stringResource(holderText)) },
        textStyle = TextStyle(color = Color.White),
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions
    )
}
