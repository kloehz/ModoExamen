package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.Promotions

interface PromotionsDataSource {
    suspend fun getPromotions(): Promotions
}