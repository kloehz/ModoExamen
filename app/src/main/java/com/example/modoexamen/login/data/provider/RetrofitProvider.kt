package com.example.modoexamen.login.data.provider

import com.example.modoexamen.application.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitProvider {

    private val okHttpClient by lazy { OkHttpProvider().provide() }

    fun initialize() {
        instance = Retrofit.Builder()
            .baseUrl(Constants.QA_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        lateinit var instance: Retrofit
    }
}