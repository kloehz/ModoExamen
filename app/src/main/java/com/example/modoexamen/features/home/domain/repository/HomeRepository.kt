package com.example.modoexamen.features.home.domain.repository

import com.example.modoexamen.features.home.data.model.Me

interface HomeRepository {
    suspend fun getMe(): Me
}