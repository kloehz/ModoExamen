package com.example.modoexamen.features.login.data.repository

import com.example.modoexamen.features.login.data.datasource.local.LoggedUserInternalDataSource
import com.example.modoexamen.features.login.data.model.KnowUser
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class LoginInternalDataRepositoryTest {
    private lateinit var loginInternalDataRepository: LoginInternalDataRepository
    private lateinit var internalDataSource: LoggedUserInternalDataSource

    @Before
    fun setup(){
        internalDataSource = mockk()
        loginInternalDataRepository = LoginInternalDataRepository(internalDataSource)
    }

    @Test
    fun `should be return an KnowUser`() {
        every { loginInternalDataRepository.getLoggedUserInfo() } returns KnowUser("37930873","Guido", "Cotelesso")
        val knowUser = loginInternalDataRepository.getLoggedUserInfo()
        assertNotNull(knowUser)
    }
}