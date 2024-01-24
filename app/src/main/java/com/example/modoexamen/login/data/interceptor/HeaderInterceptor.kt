package com.example.modoexamen.login.data.interceptor

import com.example.modoexamen.application.Constants
import com.example.modoexamen.configuration.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderInterceptor: Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
/*        val newBuild = chain.request().newBuilder()
            .addHeader("Authorization", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkpoZmhGTEF4WUJVSVc1cl9ranpadiJ9.eyJpc3MiOiJodHRwczovL3BsYXlkaWdpdGFsLXFhLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHwzNTNjYzQ5NC1jNGM1LTRmNmMtYWVmMy00ZGVlNTVhNWFmZGQiLCJhdWQiOlsiaHR0cHM6Ly9wbGF5ZGlnaXRhbC1xYS5hdXRoMCIsImh0dHBzOi8vcGxheWRpZ2l0YWwtcWEuYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTcwNTM1MjUwMSwiZXhwIjoxNzA1MzUyODAxLCJhenAiOiJLazNKU1VoUTg2aG9TUllKWWNrTFlZR3BQcEl1d2EyMiIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwgYWRkcmVzcyBwaG9uZSByZWFkOnRyYW5zZmVycyBvZmZsaW5lX2FjY2VzcyIsImd0eSI6InBhc3N3b3JkIn0.c6RVoNKc6RI0BCoMwE32fbG32lhrbkkEOXE1zzUd7pEYaERDdtaTGonjUntzgnpn0whkrFZ8-5WLGjKzaVwhngxouNLHU0DMBycgGi5lsB5xR-qo1zefQfISZlZofPYtJasSLZIplB0xdBBtAoIZYNfXx4KFGAnPVlwgEZLGptkTRjZC3GZaFLyhqBPBXqlWfmlGl0VDMyGr7p4OXiPiArGJ4OsaQNUCCyRZxsBnAtZSGY__tsGP3L6fTnoF7h1PXgant-s_iouK8tpE-m1bKb2zi8GyfA8y9iiK6pOaQU-FkCbQc8kuqjuBduxyixSpgg6MPV3BOnwGQThXC62IDg")
            .addHeader("x-api-version", "9")
            .addHeader("x-app-version", "1.79.0")
            .addHeader("x-app-build-number", "1079000")
            .addHeader("x-fingerprint", "eyJ0aW1lX3N0YW1wIjoiMjAyNC0wMS0xNlQxMjozMDo1NS40MzlaIiwiZ2VvbG9jYWxpemF0aW9uIjp7ImxhdGl0dWRlIjoiLTM0LjYwMzcyMiIsImxvbmdpdHVkZSI6Ii01OC4zODE1OTIifSwiZGV2aWNlX21vZGVsIjoic2RrX2dwaG9uZTY0X2FybTY0IiwiZGV2aWNlX25hbWUiOiJ1bmtub3duIiwib3NfbmFtZSI6ImFuZHJvaWQiLCJvc192ZXJzaW9uIjozNCwib3NfaWQiOiJkODZiOWRlNTM5NGU4ZTFkIiwibGFuZ3VhZ2UiOiJlbiIsImVtdWxhdG9yIjp0cnVlLCJoYXJkd2FyZV9pZCI6ImQ4NmI5ZGU1Mzk0ZThlMWQiLCJ1c2VyX2FnZW50IjoiTU9ET0FwcC8xLjc5LjAiLCJmY21fdG9rZW4iOiJkU0dvTU5sc1JiMkxWTURYY1RtQ1NCOkFQQTkxYkVMY3NrOGIzY3Zvazh1OWxQTmg4RUxuMG12Y0lTM3lIQjlKWXA3MVV1RVVvbWg1SzJsb0ZhVDV0RDh6cGtfQlhIN3ZIY0QxVFNaUUFRYmhZOEE4TkRuaVNLX1dVRGlGUE1SdWRoMmZReENvZkYwdVFtRlRhamdBUmg0NnJma2JpakRadmczIn0=")

        return chain.proceed(newBuild.build())*/

        val settingsHeaders = NetworkConfiguration.HEADERS

        val newRequest = chain.request().newBuilder()
            .addHeader(Constants.FINGERPRINT_HEADER_NAME, Constants.FINGERPRINT)
            .addHeader("x-api-version", "9")
            .addHeader("x-app-version", "1.79.0")
            .addHeader("x-app-build-number", "1079000")

        settingsHeaders.forEach { header ->
            newRequest.addHeader(header.key, header.value)
        }

        return chain.proceed(newRequest.build())
    }
}