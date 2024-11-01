package org.sopt.and.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes messageResId: Int) {
    Toast.makeText(this, this.getString(messageResId), Toast.LENGTH_SHORT).show()
}