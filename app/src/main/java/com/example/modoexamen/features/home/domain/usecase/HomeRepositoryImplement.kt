package com.example.modoexamen.features.home.domain.usecase

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
        if(bankAmountResponse.isSuccessful && bankAmountResponse.response != null){
            val bankAmount = dataSource.getAccountsAmount(bankId)
            val accountsById = meData.response!!.accounts.associateBy { it.id }
            bankAmount.response!!.forEach { bankAccount ->
                accountsById[bankAccount.id]?.apply {
                    balance = bankAccount.balance
                }
            }
        }
        return meData
    }
}