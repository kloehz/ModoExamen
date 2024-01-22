package com.example.modoexamen.login.data.datasource.remote

import com.example.modoexamen.login.data.model.LoginRequest
import com.example.modoexamen.login.data.model.LoginResponse

interface LoginDataSource {
    suspend fun doLogin(request: LoginRequest): LoginResponse
}