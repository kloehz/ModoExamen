package com.example.modoexamen.features.feed.data

import com.example.modoexamen.application.Constants
import com.example.modoexamen.configuration.network.interceptor.OkHttpProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedRetrofitProvider() {
    private val okHttpClient by lazy { OkHttpProvider().provide() }

    private fun initialize() {
        instance = Retrofit.Builder()
            .baseUrl(Constants.PREPROD_FEED_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var instance: Retrofit? = null
        fun getInstanceOrInitialize(): Retrofit {
            if(instance == null ){
                FeedRetrofitProvider().initialize()
                return instance!!
            } else return instance!!
        }
    }
}