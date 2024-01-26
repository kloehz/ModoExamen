package com.example.modoexamen.features.home.domain.usecase

import com.example.modoexamen.features.home.data.datasource.remote.HomeDataSource
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.domain.repository.HomeRepository

class HomeRepositoryImplement(private val dataSource: HomeDataSource): HomeRepository {
    override suspend fun getMe(): Me {
        return dataSource.getMe()
    }
}