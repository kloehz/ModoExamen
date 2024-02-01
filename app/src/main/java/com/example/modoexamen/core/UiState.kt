package com.example.modoexamen.core

import com.example.modoexamen.shared.model.ErrorCodes

sealed class UiState<out T> {
    class Initial<out T>: UiState<T>()
    class Loading<out T>: UiState<T>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Error(val error: ErrorCodes? = null): UiState<Nothing>()
}