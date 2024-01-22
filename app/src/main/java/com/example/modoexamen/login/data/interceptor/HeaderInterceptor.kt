package com.example.modoexamen.login.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuild = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ")

        return chain.proceed(newBuild.build())
    }
}