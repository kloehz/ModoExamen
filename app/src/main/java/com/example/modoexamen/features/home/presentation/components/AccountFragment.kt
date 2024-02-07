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
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModel
import com.example.modoexamen.features.home.utils.getAccountType
import com.example.modoexamen.shared.utils.formatMoney
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var homeViewModel: HomeViewModel
    private var index: Int = -1
    private lateinit var meData: Me

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
        val appContainer = (requireActivity() as MainActivity).appContainer
        homeViewModel = ViewModelProvider(requireActivity(), appContainer.homeViewModel)[HomeViewModel::class.java]

        arguments?.let{
            index = it.getInt("index")
        }
        setupObservers()
    }

    private fun setupObservers() {
        // Account observer
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeState().collect() { state ->
                    when (state) {
                        is UiState.Initial -> {

                        }
                        is UiState.Loading -> {

                        }
                        is UiState.Success -> {
                            meData = state.data
                            setupAccountComponent(meData.accounts[index])
                        }

                        is UiState.Error -> {
                        }
                    }
                }
            }
        }

        // Amount observer
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.amountsState().collect() { state ->
                    val currentAccount = state?.accounts?.get(index)
                    if(currentAccount != null){
                        if(currentAccount.balance != null && currentAccount.balanceHasLoaded) {
                            setupAmounts(currentAccount)
                        }
                        if(currentAccount.balance == null && currentAccount.balanceHasLoaded){
                            setupAmountError()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.hideAmounts.collect{ hideAmounts ->
                    if(hideAmounts) {
                        binding.hideAmountLinearLayout.visibility = View.VISIBLE
                        binding.balanceSkeleton.visibility = View.GONE
                    } else {
                        binding.hideAmountLinearLayout.visibility = View.GONE
                        binding.balanceSkeleton.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setupAmountError(){
        if(binding.amountSign.visibility == View.GONE){
            binding.amount.text = "No disponible"
            binding.balanceSkeleton.unVeil()
        }
    }

    private fun setupAmounts(account: Account){
        if(account.balance != null && account.balanceHasLoaded) {
            val (amount, cents) = formatMoney(account.balance!!)
            binding.amount.text = amount
            binding.centAmount.text = cents
            binding.balanceSkeleton.unVeil()
            binding.amountSign.visibility = View.VISIBLE
            binding.centAmount.visibility = View.VISIBLE
        }
    }

    private fun setupAccountComponent(account: Account){
        Glide.with(requireContext())
            .load(account.bank.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.bankImage)
        val accountType = getAccountType(account.type)
        binding.bankName.text = account.bank.name
        binding.accountType.text = accountType
        binding.accountTypeNumber.text = " ・ ${account.lastDigits}"
        binding.accountTypeSkeleton.unVeil()
        binding.accountType.visibility = View.VISIBLE
    }
}
