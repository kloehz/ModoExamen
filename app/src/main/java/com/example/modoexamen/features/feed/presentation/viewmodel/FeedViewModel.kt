package com.example.modoexamen.features.feed.presentation.viewmodel

import android.util.Log
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

    fun getFeed(request: FeedRequest? = null) = viewModelScope.launch {
        feedStateFlow.value = UiState.Loading()
        val result = repo.invoke(request)
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