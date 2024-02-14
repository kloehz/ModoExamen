package com.example.modoexamen.features.login.presentation.viewmodel

import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.features.login.domain.repository.LoginRepository
import com.example.modoexamen.features.login.presentation.viewmodel.LoginViewModel
import com.example.modoexamen.shared.model.ResponseResult
import org.junit.After
import org.junit.runner.RunWith
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.modoexamen.shared.model.ErrorCodes
import org.junit.Before
import org.junit.Test
import org.robolectric.annotation.Config

// Im interested on know why i need this manifest config
@Config(manifest=Config.NONE)
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginRepository: LoginRepository
    private val fakePassword = "123"
    private lateinit var fakeLoginResponse: LoginResponse

    @Before
    fun setUp(){
        loginRepository = mockk()
        viewModel = LoginViewModel(loginRepository)
        fakeLoginResponse = LoginResponse(
            accessToken = "token",
            idToken = "idToken",
            refreshToken = "refreshToken",
            expiresIn = 900,
            tokenType = "Bearer",
            identityValidation = true,
            softTokenInvalid = true,
            onboardingVersion = "V1",
            additionalData = false,
            identityDocumentInfo = false,
            lastLogin = "lastLogin",
        )
    }

    @After
    fun shutDown(){
        clearAllMocks()
    }

    @Test
    fun `viewModel doLogin returns a successfully UiState`() = runTest {
        var fakeResponse = ResponseResult<LoginResponse>()
        fakeResponse.isSuccessful = true
        fakeResponse.response = fakeLoginResponse
        coEvery { loginRepository.invoke(any()) } returns fakeResponse

        viewModel.doLogin(fakePassword)

        val loginResult = viewModel.loginState().first()
        advanceUntilIdle()
        val expectedUiState = UiState.Success(fakeLoginResponse)
        assertEquals(expectedUiState, loginResult)
    }


    @Test
    fun `viewModel doLogin returns a Error UiState`() = runTest {
        var fakeResponse = ResponseResult<LoginResponse>()
        fakeResponse.isSuccessful = false
        fakeResponse.response = fakeLoginResponse
        coEvery { loginRepository.invoke(any()) } returns fakeResponse

        viewModel.doLogin(fakePassword)

        val loginResult = viewModel.loginState().first()
        advanceUntilIdle()
        val expectedUiState = UiState.Error()
        assertEquals(expectedUiState, loginResult)
    }

    @Test
    fun `viewModel doLogin returns a Error UiState with errorCode`() = runTest {
        var fakeResponse = ResponseResult<LoginResponse>()
        fakeResponse.isSuccessful = false
        fakeResponse.response = fakeLoginResponse
        fakeResponse.internalError = ErrorCodes.external_provider_error
        coEvery { loginRepository.invoke(any()) } returns fakeResponse

        viewModel.doLogin(fakePassword)

        val loginResult = viewModel.loginState().first()
        advanceUntilIdle()
        val expectedUiState = UiState.Error(ErrorCodes.external_provider_error)
        assertEquals(expectedUiState, loginResult)
    }
}