package com.example.modoexamen.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("id_token") val idToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("identity_validation") val identityValidation: Boolean,
    @SerializedName("softtoken_invalid") val softTokenInvalid: Boolean,
    @SerializedName("onboarding_version") val onboardingVersion: String,
    @SerializedName("additional_data") val additionalData: Boolean,
    @SerializedName("identity_document_info") val identityDocumentInfo: Boolean,
    @SerializedName("last_login") val lastLogin: String
)
