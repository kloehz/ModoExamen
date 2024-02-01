package com.example.modoexamen.features.home.domain.repository

import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.shared.model.ResponseResult


internal interface PromotionsRepository {
    suspend fun invoke(): ResponseResult<Promotions>
}