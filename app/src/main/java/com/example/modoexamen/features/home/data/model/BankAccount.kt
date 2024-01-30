package com.example.modoexamen.features.home.data.model

import com.google.gson.annotations.SerializedName

data class BankAccount(
    val balance: Float,
    @SerializedName("balance_updated_at") val balanceUpdatedAt: String,
    @SerializedName("currency_code") val currencyCode: String,
    @SerializedName("feature_flags") val featureFlags: FeatureFlags,
    val features: Features,
    val id: String,
    @SerializedName("last_digits")val lastDigits: String,
    val type: String
)

data class FeatureFlags(
    val destinationAccountType: DestinationAccountType,
    val makeTransfer: Boolean
)

class Features

data class DestinationAccountType(
    val virtual: Boolean
)