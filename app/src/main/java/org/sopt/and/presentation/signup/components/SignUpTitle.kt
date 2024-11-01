package org.sopt.and.presentation.signup.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp

@Composable
fun SignUpTitle(
    firstText: String,
    firstColor: Color,
    secondText: String,
    secondColor: Color
) {
    Row {
        BasicText(
            text = buildAnnotatedString {
                append(
                    AnnotatedString(
                        text = firstText,
                        spanStyle = SpanStyle(
                            color = firstColor,
                            fontSize = 20.sp
                        )
                    )
                )
                append(
                    AnnotatedString(
                        text = secondText,
                        spanStyle = SpanStyle(
                            color = secondColor,
                            fontSize = 20.sp
                        )
                    )
                )
            }
        )
    }
}
