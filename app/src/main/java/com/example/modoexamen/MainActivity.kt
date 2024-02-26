package com.example.modoexamen

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.modoexamen.configuration.NetworkConfiguration
import com.example.modoexamen.databinding.MainActivityBinding
import com.example.modoexamen.features.feed.data.FeedRetrofitProvider
import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.home.data.provider.PromotionsRetrofitProvider
import com.example.modoexamen.features.login.data.model.KnowUser
import com.example.modoexamen.features.login.data.provider.LoginRoomProvider
import com.example.modoexamen.shared.components.NavBar.NavBarAdapter
import com.example.modoexamen.shared.providers.DataBasesProvider
import com.example.modoexamen.shared.utils.DependenciesContainer
import com.example.modoexamen.shared.utils.hide
import com.example.modoexamen.shared.utils.show
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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
        val adapter = NavBarAdapter(supportFragmentManager, lifecycle)
        binding.viewPagerTabBar.adapter = adapter
        binding.viewPagerTabBar.isUserInputEnabled = false
        binding.tabLayout.setBackgroundResource(R.color.brandGray)

        TabLayoutMediator(binding.tabLayout, binding.viewPagerTabBar) {tab, position ->
            when(position){
                0 -> {
                    tab.text = "Home"
                    tab.setIcon(R.drawable.home_tab_bar)
                }
                1 -> {
                    tab.text = "Promos"
                    tab.setIcon(R.drawable.promo_tab_bar)
                }
                2 -> {
                    tab.text = "Billetera"
                    tab.setIcon(R.drawable.wallet_tab_bar)
                }
                3 -> {
                    tab.text = "Más"
                    tab.setIcon(R.drawable.menu_tab_bar)
                }
            }
        }.attach()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController  = navHostFragment.navController

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        observeDestinationChange(navController)
    }

    private fun observeDestinationChange(navController: NavController) {
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id){
                R.id.loginFragment -> {
                    binding.viewPagerTabBar.hide()
                    binding.tabLayout.hide()
                } else -> {
                    binding.viewPagerTabBar.show()
                    binding.tabLayout.show()
                }
            }
        }
    }

    private fun initializeProviders(context: Context){
        HomeRetrofitProvider.getInstanceOrInitialize()
        PromotionsRetrofitProvider.getInstanceOrInitialize()
        FeedRetrofitProvider.getInstanceOrInitialize()
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
