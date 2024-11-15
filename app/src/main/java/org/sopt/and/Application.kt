package org.sopt.and

import android.app.Application
import org.sopt.and.domain.SharedPreferenceManager

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit var appContext: Application
            private set
    }
}