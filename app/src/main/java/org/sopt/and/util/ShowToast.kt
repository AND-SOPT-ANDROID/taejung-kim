package org.sopt.and.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(messageResId: String) {
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
}