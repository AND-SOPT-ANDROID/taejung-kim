package org.sopt.and

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WavveApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    // , SharedPreference에 사용하기 위한 Application 단위의 context를 추가
    companion object {
        lateinit var appContext: Application
            private set
    }
}