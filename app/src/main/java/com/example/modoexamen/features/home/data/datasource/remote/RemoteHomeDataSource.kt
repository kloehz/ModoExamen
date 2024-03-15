package com.example.modoexamen.features.home.data.datasource.remote

import android.util.Log
import com.example.modoexamen.features.home.data.model.BankAccount
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.data.service.HomeApiService
import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.example.modoexamen.shared.utils.handleError
import com.example.modoexamen.shared.utils.handleSuccess
import com.google.gson.Gson
import retrofit2.HttpException

internal class RemoteHomeDataSource(private val apiService: HomeApiService): HomeDataSource {
    override suspend fun getMe(): ResponseResult<Me> {
        val result = ResponseResult<Me>()
        try {
            val response = apiService.getMe()
            handleSuccess<Me>(response, result)
        }catch (e: Exception){
            handleError<Me>(e, result)
        }
        return result
    }

    override suspend fun getAccountsAmount(bankId: String): ResponseResult<List<BankAccount>> {
        val result = ResponseResult<List<BankAccount>>()
        try {
            val response = apiService.getAccountsAmount(bankId)
            handleSuccess<List<BankAccount>>(response, result)
        } catch (e: Exception) {
            handleError<List<BankAccount>>(e, result)
        }
        return result
    }
}