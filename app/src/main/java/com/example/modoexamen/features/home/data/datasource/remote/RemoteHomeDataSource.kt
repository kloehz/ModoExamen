package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.BankAccount
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.data.service.HomeApiService

class RemoteHomeDataSource(private val apiService: HomeApiService): HomeDataSource {
    override suspend fun getMe(): Me {
        return apiService.getMe()
    }

    override suspend fun getAccountsAmount(bankId: String): List<BankAccount> {
        return apiService.getAccountsAmount(bankId)
    }
}