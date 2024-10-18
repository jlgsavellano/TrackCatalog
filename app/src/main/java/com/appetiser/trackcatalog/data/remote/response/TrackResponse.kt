package com.appetiser.trackcatalog.data.remote.response

import com.appetiser.trackcatalog.data.local.entity.Track
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