package com.appetiser.trackcatalog.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.appetiser.trackcatalog.data.local.entity.Track

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