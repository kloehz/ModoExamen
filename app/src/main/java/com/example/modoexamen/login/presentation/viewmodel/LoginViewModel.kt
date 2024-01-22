package com.example.modoexamen.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.core.UiState
import com.example.modoexamen.login.data.model.LoginRequest
import com.example.modoexamen.login.data.model.LoginResponse
import com.example.modoexamen.login.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(private val repo: LoginRepository): ViewModel() {

    private val loginState = MutableStateFlow<UiState<LoginResponse>>(UiState.Loading())

    fun doLogin(data: LoginRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repo.doLogin(data)
        }.onSuccess { response ->
            loginState.value = UiState.Success(response)
        }.onFailure {loginError ->
            loginState.value = UiState.Error(Exception(loginError))
        }
    }

    fun loginResponse(): StateFlow<UiState<LoginResponse>> = loginState
}

class LoginViewModelFactory(private val repo: LoginRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(repo)
    }
}