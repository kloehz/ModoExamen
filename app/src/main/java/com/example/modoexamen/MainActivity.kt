package com.example.modoexamen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modoexamen.configuration.NetworkConfiguration
import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.home.data.provider.PromotionsRetrofitProvider
import com.example.modoexamen.utils.DependenciesContainer

class MainActivity : AppCompatActivity() {
    lateinit var appContainer: DependenciesContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        NetworkConfiguration.initialize()
        initializeProviders()
        appContainer = DependenciesContainer()
    }

    private fun initializeProviders(){
        HomeRetrofitProvider().initialize()
        PromotionsRetrofitProvider().initialize()
    }
}
