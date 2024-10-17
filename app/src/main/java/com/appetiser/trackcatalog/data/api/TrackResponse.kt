package com.appetiser.trackcatalog.data.api

import com.appetiser.trackcatalog.data.db.Track
import com.google.gson.annotations.SerializedName

/**
 * Response structure of search API
 */
data class TrackResponse(

    @SerializedName("resultCount")
    val count: Int,

    @SerializedName("results")
    val result: List<Track>
)