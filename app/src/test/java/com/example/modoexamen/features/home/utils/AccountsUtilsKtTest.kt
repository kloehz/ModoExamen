package com.example.modoexamen.features.home.utils

import com.example.modoexamen.features.home.data.model.AccountTypes
import org.junit.Test
import org.junit.Assert.assertEquals

class AccountsUtilsKtTest {

    @Test
    fun `accountUtils should be return the correct String`() {
        val savingAccount = getAccountType(AccountTypes.SAVINGS)
        val currentAccount = getAccountType(AccountTypes.CURRENT)
        assertEquals(savingAccount, "CA")
        assertEquals(currentAccount, "CC")
    }
}