package com.example.modoexamen.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import com.example.modoexamen.features.home.domain.repository.PromotionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PromotionsViewModel(private val repo: PromotionsRepository): ViewModel() {
    private val promotionsStateFlow = MutableStateFlow<UiState<Promotions>>(UiState.Initial())
    fun getPromotions() = viewModelScope.launch {
        kotlin.runCatching {
            promotionsStateFlow.value = UiState.Loading()
            repo.getPromotions()
        }.onSuccess { response ->
            promotionsStateFlow.value = UiState.Success(response)
        }.onFailure {
            promotionsStateFlow.value = UiState.Error()
        }
    }

    fun promotionsState(): StateFlow<UiState<Promotions>> = promotionsStateFlow
}

class PromotionsViewModelFactory(private val repo: PromotionsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PromotionsRepository::class.java).newInstance(repo)
    }
}