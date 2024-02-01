package com.example.modoexamen.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private val homeStateFlow = MutableStateFlow<UiState<Me>>(UiState.Initial())
    private val accountsStateFlow = MutableStateFlow<UiState<Me>>(UiState.Initial())
    fun getMe() = viewModelScope.launch {
        kotlin.runCatching {
            homeStateFlow.value = UiState.Loading()
            repo.getMe()
        }.onSuccess { response ->
            val banksIds = response.accounts.map{it.bank.id}.distinct()
            banksIds.forEach{
                getAccountsAmount(it)
            }
            homeStateFlow.value = UiState.Success(response)
        }.onFailure {_ ->
            homeStateFlow.value = UiState.Error()
        }
    }

    private fun getAccountsAmount(id: String) = viewModelScope.launch {
        kotlin.runCatching {
            accountsStateFlow.value = UiState.Loading()
            repo.getAccountsAmount(id)
        }.onSuccess {response ->
            accountsStateFlow.value = UiState.Success(response)
        }.onFailure {
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