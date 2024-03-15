package com.example.modoexamen.features.login.data.datasource.remote

import com.example.modoexamen.application.FAKE_LOGIN_DATA
import com.example.modoexamen.features.login.data.model.LoginResponse
import com.example.modoexamen.features.login.domain.usecase.LoginRepositoryImplement
import com.example.modoexamen.shared.model.ResponseResult
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.junit.Assert.assertEquals
class RemoteLoginDataSourceTest {
    private val mockDataSource: LoginDataSource = mock(LoginDataSource::class.java)
    private val repository = LoginRepositoryImplement(mockDataSource)
    @Test
    fun `doLogin returns ResponseResult with LoginResponse`() = runBlocking {
        val response = LoginResponse(
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
        val result = ResponseResult<LoginResponse>().apply {
            isSuccessful = true
            this.response = response
            code = 200
        }

        `when`(mockDataSource.doLogin(FAKE_LOGIN_DATA)).thenReturn(result)

        val actualResult = repository.invoke(FAKE_LOGIN_DATA)

        assertEquals(result, actualResult)
    }
}