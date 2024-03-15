package com.example.modoexamen.features.login.data.datasource.remote

import android.util.Log
import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.features.login.data.service.LoginApiService
import com.example.modoexamen.shared.model.ErrorResponse
import com.example.modoexamen.shared.model.ResponseResult
import com.example.modoexamen.shared.utils.handleError
import com.example.modoexamen.shared.utils.handleSuccess
import com.google.gson.Gson
import retrofit2.HttpException

internal class RemoteLoginDataSource(private val apiService: LoginApiService): LoginDataSource {
    override suspend fun doLogin(request: LoginRequest): ResponseResult<LoginResponse> {
        val result = ResponseResult<LoginResponse>()
        try {
            val response = apiService.doLogin(request)
            handleSuccess<LoginResponse>(response, result)
        }catch (e: Exception){
            handleError<LoginResponse>(e, result)
        }
        return result
    }
}