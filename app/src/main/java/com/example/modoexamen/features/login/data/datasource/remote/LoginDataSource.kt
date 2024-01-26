package com.example.modoexamen.features.login.data.datasource.remote

import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse

interface LoginDataSource {
    suspend fun doLogin(request: LoginRequest): LoginResponse
}