package com.example.modoexamen.features.login.data.repository

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.modoexamen.features.login.data.database.LoggedUserDatabase
import com.example.modoexamen.features.login.data.datasource.local.RoomInternalLoginDataSource
import com.example.modoexamen.features.login.data.model.KnowUser
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals


@RunWith(AndroidJUnit4::class)
class LoginInternalDataRepositoryTest {
    private lateinit var loginDatabase: LoggedUserDatabase
    private lateinit var internalLoginDataSource: RoomInternalLoginDataSource
    private lateinit var repository: LoginInternalDataRepository

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // get the database until the test is up, after it is destroyed
        loginDatabase = Room.inMemoryDatabaseBuilder(context, LoggedUserDatabase::class.java).build()
        internalLoginDataSource = RoomInternalLoginDataSource(loginDatabase)
        repository = LoginInternalDataRepository(internalLoginDataSource)
    }

    @After
    fun tearDown(){
        loginDatabase.close()
    }

    @Test
    fun loginUser_shouldReturnKnowUser() = runBlocking {
        val loggedUserEntity = KnowUser("37930873","Guido", "Cotelesso")
        repository.setLoggedUserInfo(loggedUserEntity)
        val result = repository.getLoggedUserInfo()

        assertEquals(loggedUserEntity, result)
    }
}