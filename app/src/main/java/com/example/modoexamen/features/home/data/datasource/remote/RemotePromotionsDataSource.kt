package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.features.home.data.service.PromotionsApiService

class RemotePromotionsDataSource(private val apiService: PromotionsApiService): PromotionsDataSource {
    override suspend fun getPromotions(): Promotions {
        return apiService.getPromotions()
    }
}
