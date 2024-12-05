package org.sopt.and.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import org.sopt.and.domain.SharedPreferenceManager.getAccessToken

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getAccessToken()
        val requestBuilder = chain.request().newBuilder()

        if (token != null) {
            requestBuilder.addHeader("token", token)
            Log.d("token", token)
        }

        return chain.proceed(requestBuilder.build())
    }
}
