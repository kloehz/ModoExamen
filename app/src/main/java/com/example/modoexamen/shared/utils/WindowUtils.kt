package com.example.modoexamen.shared.utils

import android.content.Context
import kotlin.math.sqrt

fun isSmallScreen(context: Context): Boolean {
    val width = context.resources.displayMetrics

    val screenWidthInInches = width.widthPixels / width.xdpi
    val screenHeightInInches = width.heightPixels / width.ydpi
    val screenDiagonalInInches = sqrt(
        (screenWidthInInches * screenWidthInInches + screenHeightInInches * screenHeightInInches).toDouble()
    )
    return screenDiagonalInInches < 6
}
