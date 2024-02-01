package com.example.modoexamen.features.login.domain.repository

import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.shared.model.ResponseResult

internal interface LoginRepository {
    suspend fun invoke(request: LoginRequest): ResponseResult<LoginResponse>
}