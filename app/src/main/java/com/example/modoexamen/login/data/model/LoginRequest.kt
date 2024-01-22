package com.example.modoexamen.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("dni") val dni: String,
    val email: String,
    @SerializedName("phone_number") val phoneNumber: String,
    val gender: String,
    val password: String,
    @SerializedName("login_type") val loginType: String,
)