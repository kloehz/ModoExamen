package com.example.modoexamen.shared.model

import com.google.gson.annotations.SerializedName

enum class ErrorCodes {
    external_provider_error,
    authentication_fail
}
data class ErrorResponse (
    val errors: List<Any>,
    @SerializedName("internal_code") val internalCode: ErrorCodes
)