package com.example.modoexamen.features.home.domain.usecase

import com.example.modoexamen.features.home.data.datasource.remote.PromotionsDataSource
import com.example.modoexamen.features.home.domain.repository.PromotionsRepository

class PromotionsRepositoryImplement(private val dataSource: PromotionsDataSource): PromotionsRepository {
    override suspend fun getPromotions(): Any {
        return dataSource.getPromotions()
    }

}