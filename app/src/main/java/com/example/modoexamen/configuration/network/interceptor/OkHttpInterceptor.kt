package com.example.modoexamen.configuration.network.interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

internal class OkHttpProvider {

    private val headersInterceptor = HeaderInterceptor()

    companion object {
        private const val CONNECTION_TIMEOUT_IN_SECONDS = 30L
        private const val READ_TIMEOUT_IN_SECONDS = 60L
        private const val WRITE_TIMEOUT_IN_SECONDS = 30L
    }

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provide() = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(headersInterceptor)
        .build()

}