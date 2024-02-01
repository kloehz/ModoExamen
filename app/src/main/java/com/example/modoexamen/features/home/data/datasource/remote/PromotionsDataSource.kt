package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.shared.model.ResponseResult

internal interface PromotionsDataSource {
    suspend fun getPromotions(): ResponseResult<Promotions>
}