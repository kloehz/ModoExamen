package com.example.modoexamen.utils

import com.example.modoexamen.configuration.network.provider.RetrofitProvider
import com.example.modoexamen.features.home.data.datasource.remote.RemoteHomeDataSource
import com.example.modoexamen.features.home.data.service.HomeApiService
import com.example.modoexamen.features.home.domain.usecase.HomeRepositoryImplement
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModelFactory

class DependenciesContainer {
    // Start HomeViewModel section
    val homeViewModel = HomeViewModelFactory(
        HomeRepositoryImplement(
            RemoteHomeDataSource(
                RetrofitProvider.instance.create(HomeApiService::class.java)
            )
        )
    )
    // End HomeViewModel section
}