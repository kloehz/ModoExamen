package com.example.modoexamen.features.home.data.datasource.remote

import com.example.modoexamen.features.home.data.model.Me

interface HomeDataSource {
    suspend fun getMe(): Me
}