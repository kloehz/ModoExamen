package com.example.modoexamen.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private val homeStateFlow = MutableStateFlow<UiState<Me>>(UiState.Initial())
    private val accountsStateFlow = MutableStateFlow<UiState<Me>>(UiState.Initial())
    fun getMe() = viewModelScope.launch {
        homeStateFlow.value = UiState.Loading()
        val result = repo.invokeMe()
        if(result.isSuccessful) {
            val banksIds = result.response!!.accounts.map{it.bank.id}.distinct()
            banksIds.forEach{
                getAccountsAmount(it)
            }
            homeStateFlow.value = UiState.Success(result.response!!)
        } else {
            homeStateFlow.value = UiState.Error()
        }
    }

    private fun getAccountsAmount(id: String) = viewModelScope.launch {
        accountsStateFlow.value = UiState.Loading()
        var result = repo.invokeGetAccountsAmount(id)
        if(result.isSuccessful){
            accountsStateFlow.value = UiState.Success(result.response!!)
        } else {
            accountsStateFlow.value = UiState.Error()
        }
    }


    fun homeState(): StateFlow<UiState<Me>> = homeStateFlow
    fun amountsState(): StateFlow<UiState<Me>> = accountsStateFlow
}

class HomeViewModelFactory(private val repo: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepository::class.java).newInstance(repo)
    }
}