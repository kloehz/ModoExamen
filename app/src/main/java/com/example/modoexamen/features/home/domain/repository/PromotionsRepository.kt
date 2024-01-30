package com.example.modoexamen.features.home.domain.repository

import com.example.modoexamen.features.home.data.model.Promotions


interface PromotionsRepository {
    suspend fun getPromotions(): Promotions
}