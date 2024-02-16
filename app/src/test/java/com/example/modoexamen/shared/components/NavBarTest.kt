package com.example.modoexamen.shared.components

import com.example.modoexamen.shared.components.NavBar.tabs
import org.junit.Assert
import org.junit.Test

class NavBarTest {
    @Test
    fun tabBarHasFourItems() {
        Assert.assertEquals(tabs.size, 4)
    }
}