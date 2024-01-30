package com.example.modoexamen.features.home.data.service

import com.example.modoexamen.features.home.data.model.BankAccount
import com.example.modoexamen.features.home.data.model.Me
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApiService {
    @GET(value = "users/me")
    suspend fun getMe(): Me

    @GET(value ="/banks/{bank_id}/accounts")
    suspend fun getAccountsAmount(@Path("bank_id") bankId: String): List<BankAccount>
}