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
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private val homeStateFlow = MutableStateFlow<UiState<Me>>(UiState.Initial())
    fun getMe() = viewModelScope.launch {
        kotlin.runCatching {
            homeStateFlow.value = UiState.Loading()
            repo.getMe()
        }.onSuccess { response ->
            homeStateFlow.value = UiState.Success(response)
        }.onFailure {error ->
            homeStateFlow.value = UiState.Error(Exception(error))
        }
    }

    fun homeState(): StateFlow<UiState<Me>> = homeStateFlow
}

class HomeViewModelFactory(private val repo: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepository::class.java).newInstance(repo)
    }
}