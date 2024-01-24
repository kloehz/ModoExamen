package com.example.modoexamen.login.data.service

import com.example.modoexamen.login.data.model.LoginRequest
import com.example.modoexamen.login.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST(value = "v2/auth/login")
    suspend fun doLogin(@Body loginRequest: LoginRequest): LoginResponse
}