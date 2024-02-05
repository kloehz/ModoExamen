package com.example.modoexamen.features.login.data.datasource.local

import com.example.modoexamen.features.login.data.model.KnowUser


interface LoggedUserInternalDataSource {
        fun getLoggedUserInfo(): KnowUser
        fun setLoggedUserInfo(user: KnowUser)
}