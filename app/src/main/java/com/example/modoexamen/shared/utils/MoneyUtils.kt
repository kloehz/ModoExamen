package com.example.modoexamen.shared.utils

import java.text.DecimalFormat

fun formatMoney(amount: Double): List<String> {
    val decimalFormat = DecimalFormat("#,##0.00")
    val formattedAmount = decimalFormat.format(amount)
    val cents = formattedAmount.substringAfterLast(".")
    val integerPart = formattedAmount.substringBeforeLast(".")
    return listOf(integerPart, cents)
}