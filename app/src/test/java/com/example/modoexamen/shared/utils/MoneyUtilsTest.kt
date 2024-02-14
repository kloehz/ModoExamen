package com.example.modoexamen.shared.utils

import org.junit.Test
import org.junit.Assert.assertEquals

internal class MoneyUtilsTest {
    @Test
    fun `formatMoney return last 2 digits with 00`() {
        val amount = 123.01
        val currentAmount = formatMoney(amount)
        val expectedAmount = listOf("123", "01")
        assertEquals(expectedAmount, currentAmount)
    }

    @Test
    fun `formatMoney return last 2 digits with 00 with one cent`() {
        val amount = 123.1
        val currentAmount = formatMoney(amount)
        val expectedAmount = listOf("123", "10")
        assertEquals(expectedAmount, currentAmount)
    }
}