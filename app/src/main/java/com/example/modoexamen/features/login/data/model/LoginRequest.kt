package com.example.modoexamen.features.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    val dni: String,
    val email: String,
    @SerializedName("phone_number") val phoneNumber: String,
    val gender: String,
    var password: String,
    @SerializedName("login_type") val loginType: String
)