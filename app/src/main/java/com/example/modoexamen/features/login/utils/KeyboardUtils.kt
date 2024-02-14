package com.example.modoexamen.features.login.utils

import android.content.Context
import com.example.modoexamen.R

open class KeyboardUtils(private val context: Context) {
    open fun getBiometricIconSize(): Int {
        return context.resources.getDimensionPixelOffset(R.dimen.default_icon_size)
    }
}
