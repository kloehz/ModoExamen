package com.example.modoexamen.features.login.utils

import androidx.core.content.res.TypedArrayUtils.getString
import com.example.modoexamen.R
import com.example.modoexamen.shared.model.ErrorCodes
fun getLoginErrorMessage(errorCode: ErrorCodes): Int {
    return when (errorCode) {
        ErrorCodes.external_provider_error -> R.string.general_login_error
        ErrorCodes.authentication_fail -> R.string.password_input_error
    }
}