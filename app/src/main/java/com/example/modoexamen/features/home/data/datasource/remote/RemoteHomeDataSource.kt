package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.BankAccount
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.data.service.HomeApiService
import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.google.gson.Gson
import retrofit2.HttpException

internal class RemoteHomeDataSource(private val apiService: HomeApiService): HomeDataSource {
    override suspend fun getMe(): ResponseResult<Me> {
        var result = ResponseResult<Me>()
        val gson = Gson()
        try {
            val response = apiService.getMe()
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

    override suspend fun getAccountsAmount(bankId: String): ResponseResult<List<BankAccount>> {
        var result = ResponseResult<List<BankAccount>>()
        val gson = Gson()
        try {
            val response = apiService.getAccountsAmount(bankId)
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
        } catch (e: Exception) {
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