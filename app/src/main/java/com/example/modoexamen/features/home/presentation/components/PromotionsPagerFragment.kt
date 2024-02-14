package com.example.modoexamen.features.home.presentation.components

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.modoexamen.R
import com.example.modoexamen.core.UiState
import com.example.modoexamen.databinding.FragmentPromotionsPagerBinding
import com.example.modoexamen.features.home.data.datasource.remote.RemotePromotionsDataSource
import com.example.modoexamen.features.home.data.model.PromotionCard
import com.example.modoexamen.features.home.data.provider.PromotionsRetrofitProvider
import com.example.modoexamen.features.home.data.service.PromotionsApiService
import com.example.modoexamen.features.home.domain.usecase.PromotionsRepositoryImplement
import com.example.modoexamen.features.home.presentation.viewmodel.PromotionsViewModel
import com.example.modoexamen.features.home.presentation.viewmodel.PromotionsViewModelFactory
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class PromotionsPagerFragment : Fragment(R.layout.fragment_promotions_pager) {
    private lateinit var binding: FragmentPromotionsPagerBinding
    private lateinit var viewPager: ViewPager2
    private var promotionsLength: Int = -1

    private val viewModel by viewModels<PromotionsViewModel> {
        PromotionsViewModelFactory(
            PromotionsRepositoryImplement(
                RemotePromotionsDataSource(
                    PromotionsRetrofitProvider.getInstanceOrInitialize().create(PromotionsApiService::class.java)
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPromotionsPagerBinding.bind(view)
        viewModel.getPromotions()
        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.promotionsState().collect{state ->
                    when(state) {
                        is UiState.Initial -> {}
                        is UiState.Loading -> {
                            Log.d("Promotions: ", "Loading")
                        }
                        is UiState.Success -> {
                            Log.d("Promotions: ", "Success")
                            promotionsLength = state.data.cards.size
                            setupPromotionsViewPager(state.data.cards)
                        }
                        is UiState.Error -> {}
                    }
                }
            }
        }
    }

    private fun setupPromotionsViewPager(cards: List<PromotionCard>){
        viewPager = binding.promotionsPager
        val pageTranslationX = 120
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }
        viewPager.offscreenPageLimit = 2
        viewPager.setPageTransformer(pageTransformer)
        val pagerAdapter = ScreenSlidePagerAdapter(this, cards)
        viewPager.adapter = pagerAdapter
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment, private val cardsList: List<PromotionCard>) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = promotionsLength
        override fun createFragment(position: Int): Fragment {
            val imageView = ImageView(requireContext())
            val windowWidth = resources.displayMetrics.widthPixels
            val layoutParams = ViewGroup.MarginLayoutParams(
                windowWidth - 250, (windowWidth * 0.245).roundToInt()
            )
            imageView.layoutParams = layoutParams
            Glide.with(requireContext())
                .load(cardsList[position].content.image.optionalImagesPack.landscapeApp)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .apply(RequestOptions.bitmapTransform(RoundedCorners((100  / resources.displayMetrics.density).toInt())))
                .into(imageView)
            return ImageViewFragment(imageView)
        }
    }

    class ImageViewFragment(private val imageView: ImageView) : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return imageView
        }
    }
}