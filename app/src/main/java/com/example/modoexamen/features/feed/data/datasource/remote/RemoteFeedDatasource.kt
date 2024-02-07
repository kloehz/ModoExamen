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
        var result = ResponseResult<FeedResponse>()
        //val gson = Gson()
        try {
            val response = apiService.getFeed()
            handleSuccess<FeedResponse>(response, result)
//            result.isSuccessful = response.isSuccessful
//            if(response.isSuccessful){
//                result.response = response.body()
//            } else {
//                val errorBody = response.errorBody()?.string()
//                if(errorBody != null){
//                    val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
//                    result.internalError = errorResponse.internalCode
//                }
//            }
        }catch (e: Exception){
            handleError<FeedResponse>(e, result)
//            when(e){
//                is HttpException -> {
//                    val errorBody = e.response()?.errorBody()?.string()
//                    if(errorBody != null) {
//                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
//                        result.internalError = errorResponse.internalCode
//                    } else {
//                        result.error = e.message.toString()
//                    }
//                } else -> {
//                    result.error = e.message.toString()
//                }
//            }
        }
        return result
    }
}