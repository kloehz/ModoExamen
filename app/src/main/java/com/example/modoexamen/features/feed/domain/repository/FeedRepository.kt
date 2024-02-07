package com.example.modoexamen.features.feed.domain.repository

import com.example.modoexamen.features.feed.data.model.FeedRequest
import com.example.modoexamen.features.feed.data.model.FeedResponse
import com.example.modoexamen.shared.model.ResponseResult

interface FeedRepository {
    suspend fun invoke(request: FeedRequest? = null): ResponseResult<FeedResponse>
}