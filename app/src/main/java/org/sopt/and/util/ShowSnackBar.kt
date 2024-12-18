package org.sopt.and.util

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun SnackbarHostState.showSnackBar(
    context: Context,
    scope: CoroutineScope,
    message: Int,
    actionLabel: String? = "확인"
) {
    scope.launch {
        this@showSnackBar.showSnackbar(context.getString(message), actionLabel)
    }
}