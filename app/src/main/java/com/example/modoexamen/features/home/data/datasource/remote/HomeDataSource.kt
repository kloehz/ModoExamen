package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.BankAccount
import com.example.modoexamen.features.home.data.model.Me

interface HomeDataSource {
    suspend fun getMe(): Me
    suspend fun getAccountsAmount(bankId: String): List<BankAccount>
}