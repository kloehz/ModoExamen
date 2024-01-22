package com.example.modoexamen.login.data.service

import retrofit2.http.POST

interface LoginApiService {
    @POST(value = "auth/login")
    suspend fun login()
}