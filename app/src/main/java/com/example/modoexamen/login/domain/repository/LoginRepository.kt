package com.example.modoexamen.login.domain.repository

import com.example.modoexamen.login.data.model.LoginRequest
import com.example.modoexamen.login.data.model.LoginResponse

interface LoginRepository {
    suspend fun doLogin(request: LoginRequest): LoginResponse
}