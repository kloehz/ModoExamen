package com.example.modoexamen.features.login.domain.repository

import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse

interface LoginRepository {
    suspend fun doLogin(request: LoginRequest): LoginResponse
}