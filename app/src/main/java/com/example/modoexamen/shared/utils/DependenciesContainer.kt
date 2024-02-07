package com.example.modoexamen.shared.utils

import com.example.modoexamen.features.feed.data.FeedRetrofitProvider
import com.example.modoexamen.features.feed.data.datasource.remote.RemoteFeedDatasource
import com.example.modoexamen.features.feed.data.service.FeedApiService
import com.example.modoexamen.features.feed.domain.usecase.FeedRepositoryImplement
import com.example.modoexamen.features.feed.presentation.viewmodel.FeedViewModelFactory
import com.example.modoexamen.features.home.data.provider.HomeRetrofitProvider
import com.example.modoexamen.features.home.data.datasource.remote.RemoteHomeDataSource
import com.example.modoexamen.features.home.data.service.HomeApiService
import com.example.modoexamen.features.home.domain.usecase.HomeRepositoryImplement
import com.example.modoexamen.features.home.presentation.viewmodel.HomeViewModelFactory

class DependenciesContainer {
    private val homeApiBaseUrl = HomeRetrofitProvider.instance
    val homeViewModel = HomeViewModelFactory(
        HomeRepositoryImplement(
            RemoteHomeDataSource(
                homeApiBaseUrl.create(HomeApiService::class.java)
            )
        )
    )

    private val feedApiBaseUrl = FeedRetrofitProvider.instance
    val feedViewModel = FeedViewModelFactory(
        FeedRepositoryImplement(
            RemoteFeedDatasource(
                feedApiBaseUrl.create(FeedApiService::class.java)
            )
        )
    )
}