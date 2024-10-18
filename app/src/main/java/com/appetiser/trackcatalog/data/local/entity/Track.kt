package com.appetiser.trackcatalog.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Database Model for Track
 * Using Room for persistence library
 */
@Entity(tableName = "track")
data class Track(

    @PrimaryKey
    @SerializedName("trackId")
    val id: Long = 0,

    @SerializedName("trackName")
    val name: String = "",

    @SerializedName("artworkUrl100")
    val artwork: String = "",

    @SerializedName("trackPrice")
    val price: Double = 0.0,

    @SerializedName("primaryGenreName")
    val genre: String = "",

    @SerializedName("longDescription")
    val description: String = "",

    @SerializedName("country")
    val country: String = "",

    @SerializedName("currency")
    val currency: String = "",

    val isFavorite: Boolean = false
)

