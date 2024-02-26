package com.example.modoexamen.shared.components.NavBar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.modoexamen.features.home.presentation.view.HomeFragment
import com.example.modoexamen.features.promos.presentation.view.PromotionsFragment

class NavBarAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> PromotionsFragment()
            2 -> HomeFragment()
            3 -> HomeFragment()
            else -> HomeFragment()
        }
    }
}