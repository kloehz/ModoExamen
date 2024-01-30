package com.example.modoexamen.utils

import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.home.data.datasource.remote.RemoteHomeDataSource
import com.example.modoexamen.features.home.data.service.HomeApiService
import com.example.modoexamen.features.home.domain.usecase.HomeRepositoryImplement
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModelFactory

class DependenciesContainer {
    private val apiBaseUrl = HomeRetrofitProvider.instance
    val homeViewModel = HomeViewModelFactory(
        HomeRepositoryImplement(
            RemoteHomeDataSource(
                apiBaseUrl.create(HomeApiService::class.java)
            )
        )
    )
}