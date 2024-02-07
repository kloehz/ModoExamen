package com.example.modoexamen.features.feed.data.model

import com.google.gson.annotations.SerializedName

data class FeedRequest (
    var limit: Int?,
    @SerializedName("from_date") var fromDate: String?,
    @SerializedName("to_date") var toDate: String?,
    var cursor: String?
)