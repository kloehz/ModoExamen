package com.example.modoexamen.shared.utils

import java.text.NumberFormat
import java.util.Locale

fun formatMoney(amount: Double): List<String> {
    var cents = amount.toString()
    var amountParsed = amount
    if(cents.contains(".")){
        cents = cents.takeLast(2)
        amountParsed = amountParsed.toString().dropLast(3).toDouble()
    } else {
        cents = "00"
    }
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).format(amountParsed)
    return listOf(numberFormat, cents)
}