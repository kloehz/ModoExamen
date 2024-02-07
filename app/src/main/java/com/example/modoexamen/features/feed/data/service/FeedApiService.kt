package com.example.modoexamen.features.feed.data.service

import com.example.modoexamen.features.feed.data.model.FeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeedApiService {
    @GET(value = "feed")
    suspend fun getFeed(): Response<FeedResponse>
}