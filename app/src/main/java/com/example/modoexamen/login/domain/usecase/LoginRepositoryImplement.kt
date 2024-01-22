package com.example.modoexamen.login.domain.usecase

import com.example.modoexamen.login.data.datasource.remote.LoginDataSource
import com.example.modoexamen.login.data.model.LoginRequest
import com.example.modoexamen.login.data.model.LoginResponse
import com.example.modoexamen.login.domain.repository.LoginRepository

class LoginRepositoryImplement(
    private val dataSource: LoginDataSource): LoginRepository {
    override suspend fun doLogin(request: LoginRequest): LoginResponse {
        return dataSource.doLogin(request)
    }
}