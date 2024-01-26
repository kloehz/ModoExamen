package com.example.modoexamen.features.home.utils

import android.util.Log
import com.example.modoexamen.features.home.data.model.AccountTypes

fun getAccountType(accountType: AccountTypes): String {
    Log.d("Home: ", accountType.toString())
    return when(accountType){
        AccountTypes.SAVINGS -> "CA"
        AccountTypes.CURRENT -> "CC"
    }
}