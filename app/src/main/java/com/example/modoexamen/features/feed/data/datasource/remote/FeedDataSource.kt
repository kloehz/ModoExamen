package com.example.modoexamen.features.feed.data.datasource.remote

import com.example.modoexamen.features.feed.data.model.FeedRequest
import com.example.modoexamen.features.feed.data.model.FeedResponse
import com.example.modoexamen.shared.model.ResponseResult

internal interface FeedDataSource {
    suspend fun getFeed(request: FeedRequest? = null): ResponseResult<FeedResponse>
}