package com.example.modoexamen.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.application.Constants
import com.example.modoexamen.application.FAKE_LOGIN_DATA
import com.example.modoexamen.configuration.NetworkConfiguration
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

    private val loginStateFlow = MutableStateFlow<UiState<LoginResponse>>(UiState.Loading())

    fun doLogin(password: String) = viewModelScope.launch {
        var fakeData = FAKE_LOGIN_DATA.copy()
        fakeData.password = password
        kotlin.runCatching {
            repo.doLogin(fakeData)
        }.onSuccess { response ->
            NetworkConfiguration.updateHeaders(mapOf(
                Constants.AUTHORIZATION_HEADER_NAME to response.accessToken
            ))
            loginStateFlow.value = UiState.Success(response)
        }.onFailure {loginError ->
            loginStateFlow.value = UiState.Error(Exception(loginError))
        }
    }

    fun loginState(): StateFlow<UiState<LoginResponse>> = loginStateFlow
}

class LoginViewModelFactory(private val repo: LoginRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(repo)
    }
}