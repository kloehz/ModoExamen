package com.example.modoexamen.shared.components.NavBar

import com.example.modoexamen.R

data class TabInfo(val title: String, val icon: Int, val testId: String)

val tabs = listOf(
    TabInfo("Inicio", R.drawable.home_tab_bar, "START_TEST_TAG"),
    TabInfo("Promos", R.drawable.promo_tab_bar, "PROMOS_TEST_TAG"),
    TabInfo("Billetera", R.drawable.wallet_tab_bar, "WALLET_TEST_TAG"),
    TabInfo("Más", R.drawable.menu_tab_bar, "MORE_TEST_TAG")
)