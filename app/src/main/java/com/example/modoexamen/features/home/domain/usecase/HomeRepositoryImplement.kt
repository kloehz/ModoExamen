package com.example.modoexamen.features.home.domain.usecase

import android.util.Log
import com.example.modoexamen.features.home.data.datasource.remote.HomeDataSource
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import com.example.modoexamen.shared.model.ResponseResult

internal class HomeRepositoryImplement(private val dataSource: HomeDataSource): HomeRepository {
    private lateinit var meData: ResponseResult<Me>;
    override suspend fun invokeMe(): ResponseResult<Me> {
        meData = dataSource.getMe()
        return meData
    }

    override suspend fun invokeGetAccountsAmount(bankId: String): ResponseResult<Me> {
        val bankAmountResponse = dataSource.getAccountsAmount(bankId)
        Log.d("formatMoney: ", meData.response.toString())
        meData.isSuccessful = bankAmountResponse.isSuccessful
        meData.internalError = bankAmountResponse.internalError
        val accountsById = meData.response!!.accounts.associateBy { it.id }
        if(bankAmountResponse.response != null){
            bankAmountResponse.response!!.forEach { bankAccount ->
                accountsById[bankAccount.id]?.apply {
                    balance = bankAccount.balance
                    balanceHasLoaded = true
                }
            }
        } else {
            meData.response!!.accounts.forEach {
                if(it.bank.id == bankId) {
                    it.balance = null
                    it.balanceHasLoaded = true
                }
            }
        }
        return meData
    }
}