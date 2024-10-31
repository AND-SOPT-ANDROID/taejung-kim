package org.sopt.and.domain

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        // 이미 초기화된 경우에는 초기화를 건너뛰게 설정
        if(::sharedPreferences.isInitialized){
            return
        }
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("user_id", userId).apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null)
    }
}
