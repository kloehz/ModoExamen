package com.example.modoexamen.features.login.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.modoexamen.features.login.data.model.KnowUser

@Database(entities = [KnowUser::class], version = 1, exportSchema = false)
internal abstract class LoggedUserDatabase: RoomDatabase() {
    abstract fun loggedUserDAO(): LoggedUserDao
}