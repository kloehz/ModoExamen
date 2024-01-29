package com.example.modoexamen.features.home.domain.repository


interface PromotionsRepository {
    suspend fun getPromotions(): Any
}