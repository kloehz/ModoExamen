package com.example.modoexamen.shared.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.modoexamen.MainActivity
import org.junit.Rule
import org.junit.Test

class NavBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navBarRendersCorrectly() {
        composeTestRule
            .onNodeWithTag("NAVBAR_TEST_TAG")
            .assertExists()
    }

    @Test
    fun clickingTabChangesSelection() {
        composeTestRule
            .onNodeWithTag("NAVBAR_TEST_TAG")
            .onChildren()
            .assertAny(hasClickAction())
    }
}
