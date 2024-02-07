package com.example.modoexamen.features.feed.domain.usecase

import com.example.modoexamen.features.feed.data.datasource.remote.FeedDataSource
import com.example.modoexamen.features.feed.data.model.FeedRequest
import com.example.modoexamen.features.feed.data.model.FeedResponse
import com.example.modoexamen.features.feed.domain.repository.FeedRepository
import com.example.modoexamen.shared.model.ResponseResult

internal class FeedRepositoryImplement(
    private val dataSource: FeedDataSource
) : FeedRepository {
    override suspend fun invoke(request: FeedRequest?): ResponseResult<FeedResponse> {
        return dataSource.getFeed(request)
    }
}