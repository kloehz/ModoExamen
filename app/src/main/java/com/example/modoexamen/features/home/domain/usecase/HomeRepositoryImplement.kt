package com.example.modoexamen.features.home.domain.usecase

import com.example.modoexamen.features.home.data.datasource.remote.HomeDataSource
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.domain.repository.HomeRepository

class HomeRepositoryImplement(private val dataSource: HomeDataSource): HomeRepository {
    private lateinit var meData: Me;
    override suspend fun getMe(): Me {
        meData = dataSource.getMe()
        return meData
    }

    override suspend fun getAccountsAmount(bankId: String): Me {
        val bankAmount = dataSource.getAccountsAmount(bankId)
        val accountsById = meData.accounts.associateBy { it.id }
        bankAmount.forEach { bankAccount ->
            accountsById[bankAccount.id]?.apply {
                balance = bankAccount.balance
            }
        }
        return meData
    }
}