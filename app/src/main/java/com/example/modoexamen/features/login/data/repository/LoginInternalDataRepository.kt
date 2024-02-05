package com.example.modoexamen.features.login.data.repository

import com.example.modoexamen.features.login.data.datasource.local.LoggedUserInternalDataSource
import com.example.modoexamen.features.login.data.model.KnowUser

internal class LoginInternalDataRepository(private val internalDataSource: LoggedUserInternalDataSource): LoggedUserInternalDataSource {
    override fun getLoggedUserInfo(): KnowUser {
        return internalDataSource.getLoggedUserInfo()
    }

    override fun setLoggedUserInfo(user: KnowUser) {
        internalDataSource.setLoggedUserInfo(user)
    }
}