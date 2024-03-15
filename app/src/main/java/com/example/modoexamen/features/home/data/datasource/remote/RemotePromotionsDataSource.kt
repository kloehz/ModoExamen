package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.features.home.data.service.PromotionsApiService
import com.example.modoexamen.shared.model.ResponseResult
import com.example.modoexamen.shared.utils.handleError
import com.example.modoexamen.shared.utils.handleSuccess

internal class RemotePromotionsDataSource(private val apiService: PromotionsApiService): PromotionsDataSource {
    override suspend fun getPromotions(): ResponseResult<Promotions> {
        val result = ResponseResult<Promotions>()
        try {
            val response = apiService.getPromotions()
            handleSuccess<Promotions>(response,result)
        }catch (e: Exception){
            handleError<Promotions>(e, result)
        }
        return result
    }
}
