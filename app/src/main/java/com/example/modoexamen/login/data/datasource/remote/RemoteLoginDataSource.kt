package com.example.modoexamen.login.data.datasource.remote

import com.example.modoexamen.login.data.model.LoginRequest
import com.example.modoexamen.login.data.model.LoginResponse
import com.example.modoexamen.login.data.service.LoginApiService

class RemoteLoginDataSource(private val apiService: LoginApiService): LoginDataSource {
    override suspend fun doLogin(request: LoginRequest): LoginResponse {
        return apiService.doLogin()
    }
}