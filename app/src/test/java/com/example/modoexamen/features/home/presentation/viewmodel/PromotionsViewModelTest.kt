package com.example.modoexamen.features.home.presentation.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.modoexamen.core.UiState
import com.example.modoexamen.features.home.data.model.CategoriesWhitelist
import com.example.modoexamen.features.home.data.model.Category
import com.example.modoexamen.features.home.data.model.Content
import com.example.modoexamen.features.home.data.model.Image
import com.example.modoexamen.features.home.data.model.OptionalImagesPack
import com.example.modoexamen.features.home.data.model.PrimaryPosition
import com.example.modoexamen.features.home.data.model.PromotionCard
import com.example.modoexamen.features.home.data.model.Promotions
import com.example.modoexamen.features.home.data.model.Row
import com.example.modoexamen.features.home.data.model.TagExtra
import com.example.modoexamen.features.home.data.provider.PromotionsRetrofitProvider
import com.example.modoexamen.features.home.domain.repository.PromotionsRepository
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
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PromotionsViewModelTest {
    private lateinit var viewModel: PromotionsViewModel
    private lateinit var promotionsRepository: PromotionsRepository
    private lateinit var fakePromotionsResponse: Promotions

    @Before
    fun setup() {
        promotionsRepository = mockk()
        viewModel = PromotionsViewModel(promotionsRepository)
        fakePromotionsResponse = Promotions(
            cards = listOf(
                PromotionCard(
                    backgroundColor = Any(),
                    calculatedStatus = "running",
                    categoriesWhitelist = CategoriesWhitelist(
                        categories = listOf(
                            Category(
                                mapCategory = 0,
                                subCategories = emptyList()
                            )
                        )
                    ),
                    content = Content(
                        extraRow = Any(),
                        image = Image(
                            optionalImagesPack = OptionalImagesPack(
                                landscapeApp = "https://assets.mobile.playdigital.com.ar/promotions_dynamics/images-pack/2e7e571a-1699-4c5d-a64c-a061054ebff2/landscape_app.jpg?v=1707249092528",
                                landscapeBgColor = "#FFFFFF",
                                landscapeWeb = "https://assets.mobile.playdigital.com.ar/promotions_dynamics/images-pack/2e7e571a-1699-4c5d-a64c-a061054ebff2/landscape_web.jpg?v=1707249092618"
                            ),
                            primaryBackgroundColor = "",
                            primaryImage = "https://assets.mobile.playdigital.com.ar/promotions_dynamics/29cb1352-9e01-465f-84c2-e85606d681fb/primary_image.jpg?v=1707248954661",
                            primaryPosition = listOf(
                                PrimaryPosition(x = 0, y = 0),
                                PrimaryPosition(x = 0, y = 0),
                                PrimaryPosition(x = 0, y = 40),
                                PrimaryPosition(x = 0, y = -8)
                            ),
                            secondaryBackgroundColor = "",
                            secondaryImage = "https://assets.mobile.playdigital.com.ar/promotions_dynamics/29cb1352-9e01-465f-84c2-e85606d681fb/secondary_image.png?v=1698149744414"
                        ),
                        row = listOf(
                            Row(backgroundColor = "#FFFFFF", foreColor = "#121212", text = "Cinemark Hoyts Online"),
                            Row(backgroundColor = "#FFFFFF", foreColor = "#121212", text = "2x1 y 20%"),
                            Row(backgroundColor = "#FFFFFF", foreColor = "#121212", text = "Desde el 26/10/2023 al 30/06/2024"),
                            Row(backgroundColor = "#FFFFFF", foreColor = "#414141", text = "Exclusivo con"),
                            Row(backgroundColor = "#FFFFFF", foreColor = "#121212", text = "0"),
                            Row(backgroundColor = "#FFFFFF", foreColor = "#121212", text = "Banco BBVA")
                        ),
                        tagExtras = listOf(
                            TagExtra(type = "time_range", label = "", value = false),
                            TagExtra(type = "countdown", value = false, label = ""),
                            TagExtra(type = "online_store", value = true, label = "")
                        )
                    ),
                    ctaType = "open_deeplink",
                    ctaValue = "https://www.modo.com.ar/promos/20off-cinemarkhoyts-bbva-oct23?webview=true",
                    dailyStartTime = "00:00:00-03",
                    dailyStopTime = "23:59:00-03",
                    daysOfWeek = "LMXJVSD",
                    exclusiveness = "all",
                    extraParams = Any(),
                    id = "29cb1352-9e01-465f-84c2-e85606d681fb",
                    landscapeApp = "https://assets.mobile.playdigital.com.ar/promotions_dynamics/images-pack/2e7e571a-1699-4c5d-a64c-a061054ebff2/landscape_app.jpg?v=1707249092528",
                    landscapeBgColor = "#FFFFFF",
                    landscapeWeb = "https://assets.mobile.playdigital.com.ar/promotions_dynamics/images-pack/2e7e571a-1699-4c5d-a64c-a061054ebff2/landscape_web.jpg?v=1707249092618",
                    paymentFlow = "online",
                    promoId = "602cb095-a2ae-4077-816b-3155e50763ef",
                    promoVisibility = "MBW",
                    searchTags = "cinemark,combos,entradas,web",
                    shortDescription = "20% en combos seleccionados de Cinemark Hoyts",
                    slug = "20off-cinemarkhoyts-bbva-oct23",
                    startDate = "2023-10-26T03:00:00.000Z",
                    status = "active",
                    stopDate = "2024-07-01T02:59:59.999Z",
                    suggestedOrder = 1,
                    title = "20% en combos de Hoyts",
                    triggerType = "payment_promotion",
                    type = "classic_free_content",
                    where = "Cinemark Hoyts Online"
                )
            ),
            currentPage = 1,
            currentPageCardsCount = 6,
            slotId = "app-modo-home-carrousel_principal",
            slotTitle = "APP MODO Carrusel ",
            totalCardsCount = "7",
            totalPages = 2
        )
    }

    @After
    fun shutDown() {
        clearAllMocks()
    }

    @Test
    fun `test getInstanceOrInitialize`() {
        val retrofit = PromotionsRetrofitProvider.getInstanceOrInitialize()
        assertNotNull(retrofit)
    }

    @Test
    fun `viewModel getPromotions returns a successfully UiState`() = runTest {
        val fakeResponse = ResponseResult<Promotions>()
        fakeResponse.isSuccessful = true
        fakeResponse.response = fakePromotionsResponse

        coEvery { promotionsRepository.invoke() } returns fakeResponse
        viewModel.getPromotions()

        val promotionsResult = viewModel.promotionsState().first()
        advanceUntilIdle()
        val expectedUiState = UiState.Success(fakePromotionsResponse)
        assertEquals(expectedUiState, promotionsResult)
    }

    @Test
    fun `viewModel getPromotions returns a Error UiState`() = runTest {
        val fakeResponse = ResponseResult<Promotions>()
        fakeResponse.isSuccessful = false
        fakeResponse.response = fakePromotionsResponse

        coEvery { promotionsRepository.invoke() } returns fakeResponse
        viewModel.getPromotions()

        val promotionsResult = viewModel.promotionsState().first()
        advanceUntilIdle()
        val expectedUiState = UiState.Error()
        assertEquals(expectedUiState, promotionsResult)
    }
}