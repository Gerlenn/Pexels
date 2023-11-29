package com.app.pexels.di

import com.app.pexels.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain
                .request()
                .newBuilder()
                .header(API_HEADER_NAME, BuildConfig.API_KEY)
                .build()
        )

    companion object {
        private const val API_HEADER_NAME = "Authorization"
    }
}