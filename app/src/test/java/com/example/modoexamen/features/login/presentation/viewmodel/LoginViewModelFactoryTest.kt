package com.example.modoexamen.features.login.presentation.viewmodel

import com.example.modoexamen.features.login.domain.repository.LoginRepository
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class LoginViewModelFactoryTest {

    private lateinit var mockRepository: LoginRepository
    private lateinit var factory: LoginViewModelFactory

    @Before
    fun setup() {
        mockRepository = mockk()
        factory = LoginViewModelFactory(mockRepository)
    }

    @Test
    fun `create ViewModel should return LoginViewModel`() {
        val viewModel = factory.create(LoginViewModel::class.java)
        assertNotNull(viewModel)
    }
}