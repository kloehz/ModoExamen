package com.example.modoexamen.features.login.data.provider

import android.content.Context
import androidx.room.Room
import com.example.modoexamen.features.login.data.database.LoggedUserDatabase

internal class LoginRoomProvider {
    fun initialize(context: Context) {
        instance = Room.databaseBuilder(
            context.applicationContext,
            LoggedUserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    companion object {
        lateinit var instance: LoggedUserDatabase
        private const val DATABASE_NAME = "logged_user_database"
    }
}