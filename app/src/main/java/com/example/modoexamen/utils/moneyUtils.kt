package com.example.modoexamen.utils

import java.text.NumberFormat
import java.util.Locale

fun formatMoney(amount: Int): List<String> {
    val cents = amount.toString().takeLast(2)
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).format(amount)
    return listOf(numberFormat, cents)
}