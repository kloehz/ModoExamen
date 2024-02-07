package com.example.modoexamen.application

import com.example.modoexamen.features.login.data.model.LoginRequest

object Constants {
    const val PREPROD_BASE_URL = "https://api.preprod.playdigital.com.ar/"
    const val PREPROD_PROMOTIONS_URL = "https://rewards-handler.playdigital.com.ar/"
    const val PREPROD_FEED_URL = "https://feedapi.preprod.playdigital.com.ar/"
    const val FINGERPRINT = "eyJ0aW1lX3N0YW1wIjoiMjAyNC0wMS0wNVQxOTo1ODoyMC4xOThaIiwiZ2VvbG9jYWxpemF0aW9uIjp7ImxhdGl0dWRlIjoiLTM0LjYwMzcyMiIsImxvbmdpdHVkZSI6Ii01OC4zODE1OTIifSwiZGV2aWNlX21vZGVsIjoic2RrX2dwaG9uZTY0X2FybTY0IiwiZGV2aWNlX25hbWUiOiJ1bmtub3duIiwib3NfbmFtZSI6ImFuZHJvaWQiLCJvc192ZXJzaW9uIjozNCwib3NfaWQiOiI2ZjIyZWUzYjBmNjcyOTJhIiwibGFuZ3VhZ2UiOiJlbiIsImVtdWxhdG9yIjp0cnVlLCJoYXJkd2FyZV9pZCI6IjZmMjJlZTNiMGY2NzI5MmEiLCJ1c2VyX2FnZW50IjoiTU9ET0FwcC8xLjc4LjAiLCJmY21fdG9rZW4iOiJkNkNRQWQ5bFJLS1NxOTFFeC1MSVY3OkFQQTkxYkVqYXBqdzJndmxoN0hDMEM4TFNRMFpPMHhEMzhPdTFaTjNiV1I1R2pacTI4T0FqU0hnVjVHU241a2V1VFFEY2RncnMtd0VRb2hhOE41S0VaYTJBOHIwanZJcGtvSUgyU2ZQWldBd0N0aFpyek1MVTliUHBvOGFoVE5TQzM3MWxwVEkxUGxrIn0="
    const val FINGERPRINT_HEADER_NAME = "x-fingerprint"
    const val AUTHORIZATION_HEADER_NAME = "Authorization"
}

val FAKE_LOGIN_DATA = LoginRequest(
    "40975910",
    "kloehzmu@gmail.com",
    "+5491160425418",
    "M",
    "",
    "Password"
)

//val FAKE_LOGIN_DATA = LoginRequest(
//    "39549621",
//    "kloehzmu@gmail.com",
//    "+5492213057172",
//    "M",
//    "",
//    "Password"
//)