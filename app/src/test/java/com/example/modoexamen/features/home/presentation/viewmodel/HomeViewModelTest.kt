package com.example.modoexamen.features.home.presentation.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.home.data.model.Account
import com.example.modoexamen.features.home.data.model.AccountTypes
import com.example.modoexamen.features.home.data.model.Bank
import com.example.modoexamen.features.home.data.model.Card
import com.example.modoexamen.features.home.data.model.CardArt
import com.example.modoexamen.features.home.data.model.Me
import com.example.modoexamen.features.home.data.model.SuggestedBank
import com.example.modoexamen.features.home.domain.repository.HomeRepository
import com.example.modoexamen.shared.model.ResponseResult
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeRepository: HomeRepository
    private lateinit var fakeMeResponse: Me

    @Before
    fun setup(){
        homeRepository = mockk()
        viewModel = HomeViewModel(homeRepository)
        fakeMeResponse = Me(
            accounts = listOf(
                Account(
                    bank = Bank(
                        id = "2ef15843-f60e-4905-a463-c37025c7900d",
                        imageUrl = "https://s3.amazonaws.com/assets.mobile.qa.playdigital.com.ar/images/banks/modo.png",
                        name = "Banco MODO"
                    ),
                    createdAt = "2024-01-30T16:16:11.739Z",
                    currencyCode = "ARS",
                    favourite = true,
                    id = "account_id_1",
                    balance = 100.0,
                    balanceHasLoaded = true,
                    lastDigits = "1234",
                    schema = "account_schema_1",
                    type = AccountTypes.SAVINGS
                )
            ),
            cards = listOf(
                Card(
                    bank = Any(),
                    bankLogo = "",
                    bin = "411111",
                    cardArt = CardArt(active = true),
                    cardColor = "",
                    color = Any(),
                    cvvType = "",
                    detailsAvailable = true,
                    enrollmentType = "",
                    expired = false,
                    expiry = "04/27",
                    favourite = true,
                    id = "card_id_1",
                    issuerBackgroundLogo = "",
                    issuerLogo = "",
                    issuerName = "visa",
                    lastDigits = "1111",
                    prepaid = false,
                    recentlyGushed = false,
                    type = "CREDIT"
                )
            ),
            createdAt = "2022-08-03T12:25:58.118Z",
            dni = "37930877",
            email = "kloehzmu@gmail.com",
            emailValidated = false,
            firstName = "Guido",
            gender = "M",
            id = "f54bd93f-6984-4fd6-9ba6-1e0af7b76a78",
            identityValidation = true,
            image = "https://assets.mobile.playdigital.com.ar/images/merchants/categories/Supermercados.png",
            lastName = "Cotelesso",
            licensePlates = emptyList(),
            memberGetMembersAmount = "",
            memberGetMembersMaxAmount = "",
            memberGetMembersUrl = "",
            name = "Nombre de usuario",
            phoneNumber = "+5492477566566",
            receiveBenefits = false,
            suggestedBanks = listOf(
                SuggestedBank(
                    automaticCardLinking = true,
                    canLink = true,
                    favourite = true,
                    id = "suggested_bank_id_1",
                    imageUrl = "suggested_bank_image_url_1",
                    name = "Suggested Bank 1"
                )
            ),
            suggestedBanksByCards = emptyList()
        )
    }

    @After
    fun shutDown(){
        clearAllMocks()
    }

    @Test
    fun `viewModel getMe returns a successfully UiState`() = runTest {
        val fakeResponse = ResponseResult<Me>()
        fakeResponse.isSuccessful = true
        fakeResponse.response = fakeMeResponse
        coEvery { homeRepository.invokeMe() } returns fakeResponse
        coEvery { homeRepository.invokeGetAccountsAmount("2ef15843-f60e-4905-a463-c37025c7900d") } returns fakeResponse

        viewModel.getMe()

        val meResult = viewModel.homeState().first()
        advanceUntilIdle()
        val expectedUiState = UiState.Success(fakeMeResponse)
        assertEquals(expectedUiState, meResult)
    }

    @Test
    fun `viewModel getMe returns a Error UiState`() = runTest {
        val fakeResponse = ResponseResult<Me>()
        fakeResponse.isSuccessful = false
        fakeResponse.response = fakeMeResponse
        coEvery { homeRepository.invokeMe() } returns fakeResponse
        viewModel.getMe()
        val meResult = viewModel.homeState().first()
        advanceUntilIdle()
        val expectedUiState = UiState.Error()
        assertEquals(expectedUiState, meResult)
    }
}

