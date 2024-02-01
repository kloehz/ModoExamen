package com.example.modoexamen.features.home.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.modoexamen.MainActivity
import com.example.modoexamen.R
import com.example.modoexamen.core.UiState
import com.example.modoexamen.databinding.FragmentHomeBinding
import com.example.modoexamen.features.home.presentation.components.AccountFragment
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewPager: ViewPager2
    private var accountsLength: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val appContainer = (requireActivity() as MainActivity).appContainer
        viewModel = ViewModelProvider(requireActivity(), appContainer.homeViewModel)[HomeViewModel::class.java]
        setupObservers()
    }

    private fun setupObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.homeState().collect{state ->
                    when(state) {
                        is UiState.Initial -> {}
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            accountsLength = state.data.accounts.size
                            setupAccountsViewPager()
                        }
                        is UiState.Error -> {

                        }
                    }
                }
            }
        }
    }

    private fun setupAccountsViewPager(){
        viewPager = binding.accountsPager
        val pageTranslationX = 250
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }
        viewPager.offscreenPageLimit = 1
        viewPager.setPageTransformer(pageTransformer)
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = accountsLength
        override fun createFragment(position: Int): Fragment {
            val fragment = AccountFragment()
            val bundle = Bundle()
            bundle.putInt("index", position)
            fragment.arguments = bundle
            return fragment
        }
    }
}