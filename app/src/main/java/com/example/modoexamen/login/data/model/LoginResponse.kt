package com.example.modoexamen.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") val access_token: String,
    @SerializedName("id_token") val id_token: String,
    @SerializedName("refresh_token") val refresh_token: String,
    @SerializedName("expires_in") val expires_in: Int,
    @SerializedName("token_type") val token_type: String,
    @SerializedName("identity_validation") val identity_validation: Boolean,
    @SerializedName("softtoken_invalid") val softtoken_invalid: Boolean,
    @SerializedName("onboarding_version") val onboarding_version: String,
    @SerializedName("additional_data") val additional_data: Boolean,
    @SerializedName("identity_document_info") val identity_document_info: Boolean,
    @SerializedName("last_login") val last_login: String
)
