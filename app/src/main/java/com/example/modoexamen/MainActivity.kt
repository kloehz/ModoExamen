package com.example.modoexamen

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.modoexamen.configuration.NetworkConfiguration
import com.example.modoexamen.features.feed.data.FeedRetrofitProvider
import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.home.data.provider.PromotionsRetrofitProvider
import com.example.modoexamen.features.login.data.model.KnowUser
import com.example.modoexamen.features.login.data.provider.LoginRoomProvider
import com.example.modoexamen.shared.providers.DataBasesProvider
import com.example.modoexamen.shared.utils.DependenciesContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var appContainer: DependenciesContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        NetworkConfiguration.initialize()
        initializeProviders(this)
        appContainer = DependenciesContainer()
    }

    private fun initializeProviders(context: Context){
        HomeRetrofitProvider().initialize()
        PromotionsRetrofitProvider().initialize()
        FeedRetrofitProvider().initialize()
        LoginRoomProvider().initialize(context)

        // Execute the query in IO thread
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val loggedUserDao = DataBasesProvider(context).provide()
                loggedUserDao.setLoggedUserInfo(KnowUser("37930873","Guido", "Cotelesso"))
            }
        }
    }
}
