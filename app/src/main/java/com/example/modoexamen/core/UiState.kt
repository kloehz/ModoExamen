package com.example.modoexamen.core

sealed class UiState<out T> {
    class Initial<out T>: UiState<T>()
    class Loading<out T>: UiState<T>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Error(val exception: Exception): UiState<Nothing>()
}