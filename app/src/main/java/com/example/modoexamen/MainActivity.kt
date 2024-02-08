package com.example.modoexamen

import android.content.Context
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.modoexamen.configuration.NetworkConfiguration
import com.example.modoexamen.databinding.MainActivityBinding
import com.example.modoexamen.features.feed.data.FeedRetrofitProvider
import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.home.data.provider.PromotionsRetrofitProvider
import com.example.modoexamen.features.login.data.model.KnowUser
import com.example.modoexamen.features.login.data.provider.LoginRoomProvider
import com.example.modoexamen.shared.components.NavBar
import com.example.modoexamen.shared.providers.DataBasesProvider
import com.example.modoexamen.shared.utils.DependenciesContainer
import com.example.modoexamen.shared.utils.hide
import com.example.modoexamen.shared.utils.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var appContainer: DependenciesContainer
    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavBar()
        NetworkConfiguration.initialize()
        initializeProviders(this)
        appContainer = DependenciesContainer()
    }

    private fun setupNavBar(){
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationBar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController  = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        val composeContainer: FrameLayout = findViewById(R.id.appbarComposeContainer)
        val composeView = ComposeView(this)
        composeContainer.addView(composeView)

        composeView.setContent {
            NavBar(navController)
        }
        observeDestinationChange(navController)
    }

    private fun observeDestinationChange(navController: NavController) {
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id){
                R.id.loginFragment -> {
                    binding.bottomNavigationBar.hide()
                } else -> {
                    binding.bottomNavigationBar.show()
                }
            }
        }
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
