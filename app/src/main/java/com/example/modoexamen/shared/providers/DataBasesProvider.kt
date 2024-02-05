package com.example.modoexamen.shared.providers

import android.content.Context
import androidx.room.Room
import com.example.modoexamen.features.login.data.database.LoggedUserDatabase
import com.example.modoexamen.features.login.data.datasource.local.LoggedUserInternalDataSource
import com.example.modoexamen.features.login.data.datasource.local.RoomInternalLoginDataSource
import com.example.modoexamen.features.login.data.repository.LoginInternalDataRepository

class DataBasesProvider(context: Context) {
    private val localDataSource: LoggedUserInternalDataSource by lazy {
        val db = Room.databaseBuilder(
            context.applicationContext,
            LoggedUserDatabase::class.java,
            DATABASE_NAME
        ).build()

        RoomInternalLoginDataSource(db)
    }

    fun provide(): LoggedUserInternalDataSource = LoginInternalDataRepository(localDataSource)

    companion object{
        private const val DATABASE_NAME = "modo_examen_logged-user"
    }
}