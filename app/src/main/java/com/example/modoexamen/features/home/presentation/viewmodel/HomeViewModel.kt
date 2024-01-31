package com.example.modoexamen.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private val homeStateFlow = MutableSharedFlow<UiState<Me>>(replay = 1)
    fun getMe() = viewModelScope.launch {
        kotlin.runCatching {
            homeStateFlow.emit(UiState.Loading())
            repo.getMe()
        }.onSuccess { response ->
            val banksIds = response.accounts.map{it.bank.id}.distinct()
            banksIds.forEach{
                getAccountsAmount(it)
            }
            homeStateFlow.emit(UiState.Success(response))
        }.onFailure {error ->
            homeStateFlow.emit(UiState.Error(Exception(error)))
        }
    }

    private fun getAccountsAmount(id: String) = viewModelScope.launch {
        kotlin.runCatching {
            homeStateFlow.emit(UiState.Loading())
            repo.getAccountsAmount(id)
        }.onSuccess {response ->
            Log.d("response: ", response.toString())
            homeStateFlow.emit(UiState.Success(response))
        }.onFailure {
            val lastValue: UiState<Me>? = homeStateFlow.firstOrNull()
            if (lastValue is UiState.Success<Me>) {
                val meData: Me = lastValue.data
                // We need another state here to fix when EP throws error
                homeStateFlow.emit(UiState.Success(meData))
            }
        }
    }

    fun homeState(): SharedFlow<UiState<Me>> = homeStateFlow
}

class HomeViewModelFactory(private val repo: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepository::class.java).newInstance(repo)
    }
}