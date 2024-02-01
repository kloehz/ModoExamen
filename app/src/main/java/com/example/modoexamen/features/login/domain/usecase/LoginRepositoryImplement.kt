package com.example.modoexamen.features.login.domain.usecase

import com.example.modoexamen.features.login.data.datasource.remote.LoginDataSource
import com.example.modoexamen.features.login.data.model.LoginRequest
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.features.login.domain.repository.LoginRepository
import com.example.modoexamen.shared.model.ResponseResult

internal class LoginRepositoryImplement(
    private val dataSource: LoginDataSource
) : LoginRepository {
    override suspend fun invoke(request: LoginRequest): ResponseResult<LoginResponse> {
        return dataSource.doLogin(request)

    }
}