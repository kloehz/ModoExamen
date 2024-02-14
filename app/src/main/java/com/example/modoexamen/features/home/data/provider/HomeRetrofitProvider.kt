package com.example.modoexamen.features.home.data.provider

import com.example.modoexamen.application.Constants
import com.example.modoexamen.configuration.network.interceptor.OkHttpProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class HomeRetrofitProvider() {
    private val okHttpClient by lazy { OkHttpProvider().provide() }

    private fun initialize() {
        instance = Retrofit.Builder()
            .baseUrl(Constants.PREPROD_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var instance: Retrofit? = null
        fun getInstanceOrInitialize(): Retrofit {
            if(instance == null ){
                HomeRetrofitProvider().initialize()
                return instance!!
            } else return instance!!
        }
    }
}