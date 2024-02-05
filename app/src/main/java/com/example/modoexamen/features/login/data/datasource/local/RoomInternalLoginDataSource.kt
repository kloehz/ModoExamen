package com.example.modoexamen.features.login.data.datasource.local

import com.example.modoexamen.features.login.data.database.LoggedUserDatabase
import com.example.modoexamen.features.login.data.model.KnowUser

internal class RoomInternalLoginDataSource(private val loggedUserDatabase: LoggedUserDatabase):
    LoggedUserInternalDataSource {
    override fun getLoggedUserInfo(): KnowUser {
        return loggedUserDatabase.loggedUserDAO().getLoggedUserInfo()
    }

    override fun setLoggedUserInfo(user: KnowUser) {
        loggedUserDatabase.loggedUserDAO().setLoggedUserInfo(user)
    }

}