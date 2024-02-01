package com.example.modoexamen.features.home.domain.repository

import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.shared.model.ResponseResult

interface HomeRepository {
    suspend fun invokeMe(): ResponseResult<Me>
    suspend fun invokeGetAccountsAmount(bankId: String): ResponseResult<Me>
}