package com.example.modoexamen.configuration.network.interceptor

import com.example.modoexamen.application.Constants
import com.example.modoexamen.configuration.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderInterceptor: Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val settingsHeaders = NetworkConfiguration.HEADERS

        val newRequest = chain.request().newBuilder()
            .addHeader(Constants.FINGERPRINT_HEADER_NAME, Constants.FINGERPRINT)
            .addHeader("x-api-version", "9")
            .addHeader("x-app-version", "1.79.0")
            .addHeader("x-app-build-number", "1079000")
        settingsHeaders.forEach { header ->
            newRequest.addHeader(header.key, header.value)
        }

        return chain.proceed(newRequest.build())
    }
}