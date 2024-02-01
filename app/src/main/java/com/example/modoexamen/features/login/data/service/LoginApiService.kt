package com.example.modoexamen.features.login.data.service

import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response

interface LoginApiService {
    @POST(value = "v2/auth/login")
    suspend fun doLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>
}