package com.example.modoexamen.features.home.data.service

import com.example.modoexamen.features.home.data.model.Me
import retrofit2.http.GET

interface HomeApiService {
    @GET(value = "users/me")
    suspend fun getMe(): Me
}