package com.example.modoexamen.configuration

import com.example.modoexamen.application.Constants

object NetworkConfiguration {
    lateinit var HEADERS: Map<String, String>

    @JvmStatic
    fun initialize() {
        this.HEADERS = mapOf(
            Constants.FINGERPRINT_HEADER_NAME to Constants.FINGERPRINT,
        )
    }

    @JvmStatic
    fun updateHeaders(headers: Map<String, String>) {
        this.HEADERS = headers.toMutableMap()
    }
}