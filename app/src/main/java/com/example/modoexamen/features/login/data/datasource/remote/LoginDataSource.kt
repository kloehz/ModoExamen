package com.example.modoexamen.features.login.data.datasource.remote

import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.shared.model.ResponseResult

internal interface LoginDataSource {
    suspend fun doLogin(request: LoginRequest): ResponseResult<LoginResponse>
}