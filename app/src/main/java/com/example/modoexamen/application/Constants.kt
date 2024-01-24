package com.example.modoexamen.application

import com.example.modoexamen.login.data.model.LoginRequest

object Constants {
    const val QA_BASE_URL = "https://api.qa.playdigital.com.ar/"
    const val FINGERPRINT = "eyJ0aW1lX3N0YW1wIjoiMjAyNC0wMS0xNlQxMjozMDo1NS40MzlaIiwiZ2VvbG9jYWxpemF0aW9uIjp7ImxhdGl0dWRlIjoiLTM0LjYwMzcyMiIsImxvbmdpdHVkZSI6Ii01OC4zODE1OTIifSwiZGV2aWNlX21vZGVsIjoic2RrX2dwaG9uZTY0X2FybTY0IiwiZGV2aWNlX25hbWUiOiJ1bmtub3duIiwib3NfbmFtZSI6ImFuZHJvaWQiLCJvc192ZXJzaW9uIjozNCwib3NfaWQiOiJkODZiOWRlNTM5NGU4ZTFkIiwibGFuZ3VhZ2UiOiJlbiIsImVtdWxhdG9yIjp0cnVlLCJoYXJkd2FyZV9pZCI6ImQ4NmI5ZGU1Mzk0ZThlMWQiLCJ1c2VyX2FnZW50IjoiTU9ET0FwcC8xLjc5LjAiLCJmY21fdG9rZW4iOiJkU0dvTU5sc1JiMkxWTURYY1RtQ1NCOkFQQTkxYkVMY3NrOGIzY3Zvazh1OWxQTmg4RUxuMG12Y0lTM3lIQjlKWXA3MVV1RVVvbWg1SzJsb0ZhVDV0RDh6cGtfQlhIN3ZIY0QxVFNaUUFRYmhZOEE4TkRuaVNLX1dVRGlGUE1SdWRoMmZReENvZkYwdVFtRlRhamdBUmg0NnJma2JpakRadmczIn0="
    const val FINGERPRINT_HEADER_NAME = "x-fingerprint"
    const val AUTHORIZATION_HEADER_NAME = "Authorization"
}

val FAKE_LOGIN_DATA = LoginRequest(
    "37930873",
    "kloehzmu@gmail.com",
    "+5492477566666",
    "M",
    "",
    "Password"
)