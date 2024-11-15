package org.sopt.and.domain

import android.content.Context
import org.sopt.and.Application

object SharedPreferenceManager {
    private const val PREF_NAME = "app_preferences"
    private val preferences = Application.appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        preferences.edit().putString("token", token).apply()
    }

    fun saveId(userId: String) {
        preferences.edit().putString("userId", userId).apply()
    }

    fun getAccessToken(): String? {
        return preferences.getString("token", null)
    }
}