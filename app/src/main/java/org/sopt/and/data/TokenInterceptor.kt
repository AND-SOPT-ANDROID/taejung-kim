package org.sopt.and.data

import okhttp3.Interceptor
import okhttp3.Response
import org.sopt.and.domain.SharedPreferenceManager

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SharedPreferenceManager.getAccessToken()
        val requestBuilder = chain.request().newBuilder()

        if (token != null) {
            requestBuilder.addHeader("token", token)
        }

        return chain.proceed(requestBuilder.build())
    }
}
