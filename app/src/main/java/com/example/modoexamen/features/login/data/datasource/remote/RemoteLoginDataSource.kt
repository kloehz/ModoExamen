package com.example.modoexamen.features.login.data.datasource.remote

import android.util.Log
import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.features.login.data.service.LoginApiService
import com.example.modoexamen.shared.model.ErrorCodes
import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.awaitResponse

internal class RemoteLoginDataSource(private val apiService: LoginApiService): LoginDataSource {
    override suspend fun doLogin(request: LoginRequest): ResponseResult<LoginResponse> {
        var result = ResponseResult<LoginResponse>()
        try {
            val response = apiService.doLogin(request)
            result.isSuccessful = response.isSuccessful
            if(response.isSuccessful) {
                result.isSuccessful = true
                result.response = response.body()
            } else {
                val gson = Gson()
                val errorBody = response.errorBody()?.string()
                if(errorBody != null) {
                    val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                    result.internalError = errorResponse.internalCode
                }
                result.error = response.errorBody().toString()
            }
        }catch (e: Exception){
            when(e){
                is HttpException -> {
                    val gson = Gson()
                    val errorBody = e.response()?.errorBody()?.string()
                    if(errorBody != null) {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        result.internalError = errorResponse.internalCode
                    }
                } else -> {
                    result.error = e.message.toString()
                }
            }
        }
        return result
    }
}