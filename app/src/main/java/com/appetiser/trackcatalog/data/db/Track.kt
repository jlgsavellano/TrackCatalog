package com.appetiser.trackcatalog.data.db

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
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

/**
 * Data Access Object for Track
 * Contains INSERT, SELECT and UPDATE queries
 */
@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(tracks: List<Track>)

    @Query("SELECT * FROM track WHERE country LIKE :country ORDER BY genre ASC, isFavorite DESC, name ASC")
    suspend fun getAllTracks(country: String): List<Track>

    @Query("SELECT * FROM track WHERE id LIKE :trackId")
    suspend fun getTrack(trackId: Long): Track

    @Update
    suspend fun updateTrack(track: Track)
}