package com.example.modoexamen.login.data.service

import com.example.modoexamen.login.data.model.LoginResponse
import retrofit2.http.POST

interface LoginApiService {
    @POST(value = "auth/login")
    suspend fun doLogin(): LoginResponse
}