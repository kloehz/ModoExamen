package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.BankAccount
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.shared.model.ResponseResult

internal interface HomeDataSource {
    suspend fun getMe(): ResponseResult<Me>
    suspend fun getAccountsAmount(bankId: String): ResponseResult<List<BankAccount>>
}