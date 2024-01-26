package com.example.modoexamen.features.home.presentation.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.modoexamen.MainActivity
import com.example.modoexamen.R
import com.example.modoexamen.core.UiState
import com.example.modoexamen.databinding.FragmentAccountBinding
import com.example.modoexamen.features.home.data.model.Account
import com.example.modoexamen.features.home.data.model.AccountTypes
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModel
import com.example.modoexamen.features.home.utils.getAccountType
import kotlinx.coroutines.launch
class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var homeViewModel: HomeViewModel
    private var index: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)

        val appContainer = (requireActivity() as MainActivity).appContainer
        homeViewModel = ViewModelProvider(requireActivity(), appContainer.homeViewModel)[HomeViewModel::class.java]

        arguments?.let{argumentIndex ->
            index = argumentIndex.getInt("index")
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeState().collect() { state ->
                    when (state) {
                        is UiState.Initial -> {
                            Log.d("Account: ", "Initial")}
                        is UiState.Loading -> {
                            Log.d("Account: ", "Loading")
                        }
                        is UiState.Success -> {
                            Log.d("Account: ", "Success")
                            setupComponent(state.data.accounts[index])
                        }

                        is UiState.Error -> {}
                    }
                }
            }
        }
    }

    private fun setupComponent(account: Account){
        Glide.with(requireContext())
            .load(account.bank.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.bankImage)
        binding.accountTypeNumber.text = account.lastDigits
        val accountType = getAccountType(account.type)
        binding.accountType.text = "$accountType ・ "
    }
}
