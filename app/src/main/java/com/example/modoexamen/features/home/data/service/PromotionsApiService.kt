package com.example.modoexamen.features.home.data.service

import com.example.modoexamen.features.home.data.model.Promotions
import retrofit2.Response
import retrofit2.http.GET

interface PromotionsApiService {
    @GET(value = "slots/app-modo-home-carrousel_principal")
    suspend fun getPromotions(): Response<Promotions>
}