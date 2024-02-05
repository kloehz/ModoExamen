package com.example.modoexamen.features.login.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "loggedUser")
@Parcelize
data class KnowUser (
    @PrimaryKey val dni: String,
    val firstName: String,
    val lastName: String
): Parcelable