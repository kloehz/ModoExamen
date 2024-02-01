package com.example.modoexamen.features.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.modoexamen.application.Constants
import com.example.modoexamen.application.FAKE_LOGIN_DATA
import com.example.modoexamen.configuration.NetworkConfiguration
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class LoginViewModel(private val repo: LoginRepository): ViewModel() {

    private val loginStateFlow = MutableStateFlow<UiState<LoginResponse>>(UiState.Initial())

    fun doLogin(password: String) = viewModelScope.launch {
        loginStateFlow.value = UiState.Loading()
        var fakeData = FAKE_LOGIN_DATA.copy()
        fakeData.password = password
        val result = repo.invoke(fakeData)
        if(result.isSuccessful){
            NetworkConfiguration.updateHeaders(mapOf(
                Constants.AUTHORIZATION_HEADER_NAME to result.response!!.accessToken
            ))
            loginStateFlow.value = UiState.Success(result.response!!)
        } else {
            loginStateFlow.value = UiState.Error(result.internalError)
        }
    }

    fun loginState(): StateFlow<UiState<LoginResponse>> = loginStateFlow
}

internal class LoginViewModelFactory(private val repo: LoginRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(repo)
    }
}