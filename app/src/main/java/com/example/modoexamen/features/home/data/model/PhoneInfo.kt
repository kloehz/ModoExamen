package com.example.modoexamen.features.home.data.model

import com.google.gson.annotations.SerializedName

data class PhoneInfo(
    @SerializedName("phone_number") val phoneNumber: String,
    val dni: String,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val gender: String,
)

