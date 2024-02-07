package com.example.modoexamen.features.feed.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.feed.data.model.FeedRequest
import com.example.modoexamen.features.feed.data.model.FeedResponse
import com.example.modoexamen.features.feed.domain.repository.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class FeedViewModel(private val repo: FeedRepository): ViewModel() {
    private val feedStateFlow = MutableStateFlow<UiState<FeedResponse>>(UiState.Initial())
    // Example to know how to composable items can change state
    // var isShowingShimmer by mutableStateOf(true)

    fun getFeed(request: FeedRequest? = null) = viewModelScope.launch {
        feedStateFlow.value = UiState.Loading()
        var result = repo.invoke(request)
        if( result.isSuccessful ) {
            feedStateFlow.value = UiState.Success(result.response!!)
        } else {
            feedStateFlow.value = UiState.Error(result.internalError)
        }
    }

    fun feedState(): StateFlow<UiState<FeedResponse>> = feedStateFlow
}

class FeedViewModelFactory(private val repo: FeedRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FeedRepository::class.java).newInstance(repo)
    }
}