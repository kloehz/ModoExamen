package com.example.modoexamen.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private val homeStateFlow = MutableStateFlow<UiState<Me>>(UiState.Initial())
    private val accountsStateFlow = MutableSharedFlow<Me?>(replay = 1)

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
        var result = repo.invokeGetAccountsAmount(id)
        accountsStateFlow.emit(result.response)
    }

    fun homeState(): StateFlow<UiState<Me>> = homeStateFlow
    fun amountsState(): MutableSharedFlow<Me?> = accountsStateFlow
}

class HomeViewModelFactory(private val repo: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepository::class.java).newInstance(repo)
    }
}



