package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.features.home.data.service.PromotionsApiService
import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.google.gson.Gson
import retrofit2.HttpException

internal class RemotePromotionsDataSource(private val apiService: PromotionsApiService): PromotionsDataSource {
    override suspend fun getPromotions(): ResponseResult<Promotions> {
        var result = ResponseResult<Promotions>()
        val gson = Gson()
        try {
            val response = apiService.getPromotions()
            result.isSuccessful = response.isSuccessful
            if(response.isSuccessful) {
                result.response = response.body()
            } else {
                val errorBody = response.errorBody()?.string()
                if(errorBody != null) {
                    val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                    result.internalError = errorResponse.internalCode
                }
            }
        }catch (e: Exception){
            when(e) {
                is HttpException -> {
                    val errorBody = e.response()?.errorBody()?.string()
                    if(errorBody != null) {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        result.internalError = errorResponse.internalCode
                    } else {
                        result.error = e.message.toString()
                    }
                } else -> {
                result.error = e.message.toString()
            }
            }
        }
        return result
    }
}
