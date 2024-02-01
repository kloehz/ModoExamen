package com.example.modoexamen.features.home.domain.usecase

import com.example.modoexamen.features.home.data.datasource.remote.PromotionsDataSource
import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.features.home.domain.repository.PromotionsRepository
import com.example.modoexamen.shared.model.ResponseResult

internal class PromotionsRepositoryImplement(private val dataSource: PromotionsDataSource): PromotionsRepository {
    override suspend fun invoke(): ResponseResult<Promotions> {
        return dataSource.getPromotions()
    }

}