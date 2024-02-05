package com.example.modoexamen.features.login.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.modoexamen.features.login.data.model.KnowUser

@Dao
interface LoggedUserDao {
    @Query("SELECT * FROM loggedUser")
    fun getLoggedUserInfo(): KnowUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setLoggedUserInfo(vararg user: KnowUser)
}