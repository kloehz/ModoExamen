package com.example.modoexamen.application

import com.example.modoexamen.login.data.model.LoginRequest

object Constants {
    const val QA_BASE_URL = "https://api.qa.playdigital.com.ar/"
}

val FAKE_LOGIN_DATA = LoginRequest(
    "37930873",
    "kloehzmu@gmail.com",
    "+5492477566666",
    "M",
    "",
    "Password"
)