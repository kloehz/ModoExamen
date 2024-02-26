package com.example.modoexamen.features.feed.data.datasource.remote

import com.example.modoexamen.features.feed.data.model.FeedRequest
import com.example.modoexamen.features.feed.data.model.FeedResponse
import com.example.modoexamen.features.feed.data.service.FeedApiService
import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.example.modoexamen.shared.utils.handleError
import com.example.modoexamen.shared.utils.handleSuccess
import com.google.gson.Gson
import retrofit2.HttpException

internal class RemoteFeedDatasource(private val apiService: FeedApiService): FeedDataSource {
    override suspend fun getFeed(request: FeedRequest?): ResponseResult<FeedResponse> {
        val result = ResponseResult<FeedResponse>()
        try {
            val response = apiService.getFeed()
            handleSuccess<FeedResponse>(response, result)
        }catch (e: Exception){
            handleError<FeedResponse>(e, result)
        }
        return result
    }
}