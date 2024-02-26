package com.example.modoexamen.features.home.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.ui.platform.ComposeView
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
import com.example.modoexamen.features.feed.data.model.FeedResponseItem
import com.example.modoexamen.features.feed.presentation.components.FeedItemComposable
import com.example.modoexamen.features.feed.presentation.viewmodel.FeedViewModel
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.presentation.components.AccountFragment
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModel
import com.example.modoexamen.shared.utils.isSmallScreen
import kotlinx.coroutines.launch
import androidx.fragment.app.FragmentManager

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var feedViewModel: FeedViewModel
    private var accountsLength: Int = 0
    private var meData: Me? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val appContainer = (requireActivity() as MainActivity).appContainer
        viewModel = ViewModelProvider(requireActivity(), appContainer.homeViewModel)[HomeViewModel::class.java]
        feedViewModel = ViewModelProvider(requireActivity(), appContainer.feedViewModel)[FeedViewModel::class.java]
        setupHomeObserver()
        setupFeedObserver()
        setupComponents()
    }

    private fun setupComponents(){
        setupShimmerFeed()

        binding.hideAmmountsButton.setOnClickListener{
            viewModel.hideAmounts.value = !viewModel.hideAmounts.value
        }
    }

    private fun setupHomeObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.homeState().collect{state ->
                    when(state) {
                        is UiState.Initial -> {
                            feedViewModel.getFeed()
                        }
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            accountsLength = state.data.accounts.size
                            meData = state.data
                            setupAccountsViewPager()
                        }
                        is UiState.Error -> {

                        }
                    }
                }
            }
        }
    }

    private fun setupFeedObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                feedViewModel.feedState().collect { state ->
                        when(state){
                            is UiState.Initial -> {}
                            is UiState.Loading -> {}
                            is UiState.Success -> {
                                setupFeed(state.data)
                            }
                            is UiState.Error -> {}
                        }
                }
            }
        }
    }

    private fun setupAccountsViewPager(){
        viewPager = binding.accountsPager
        val pageSpacer = if(isSmallScreen(requireContext())) R.dimen.viewpager_next_item_visible_small_device else R.dimen.viewpager_next_item_visible_large_device
        val pageTranslationX = resources.getDimensionPixelOffset(pageSpacer) //250
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }
        viewPager.offscreenPageLimit = 1
        viewPager.setPageTransformer(pageTransformer)
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    private fun setupShimmerFeed() {
        binding.feedItem.setContent {
            FeedItemComposable(feedItem = null, showShimmer = true)
        }
    }

    private fun setupFeed(feedItem: List<FeedResponseItem>){
        binding.feedItem.visibility = View.GONE
        val feedSize = if(feedItem.size > 5) 5 else feedItem.size
        for(i in 0 until feedSize){
            var composeView = ComposeView(requireContext())
            composeView.setContent {
                FeedItemComposable(feedItem = feedItem[i])
            }
            binding.feedLinearLayout.addView(composeView)
        }
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