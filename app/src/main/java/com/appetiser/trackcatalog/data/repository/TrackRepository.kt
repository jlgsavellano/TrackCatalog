package com.appetiser.trackcatalog.data.repository

import com.appetiser.trackcatalog.data.api.AppleApiService
import com.appetiser.trackcatalog.data.db.Track
import com.appetiser.trackcatalog.data.db.TrackDao
import javax.inject.Inject

/**
 * Makes this class injectable to be used on ViewModel
 */
class TrackRepository @Inject constructor(
    private val apiService: AppleApiService,
    private val dao: TrackDao
) {

    suspend fun searchTracks(term: String, country: String): List<Track> {
        val response = apiService.search(term, country, media = "movie")

        if (response.isSuccessful) {
            return response.body()?.result?.also { tracks ->
                dao.insertAll(tracks)
            } ?: emptyList()
        }

        return emptyList()
    }

    suspend fun getAllTracks(country: String): List<Track> = dao.getAllTracks("%$country%")

    suspend fun getTrack(trackId: Long): Track = dao.getTrack(trackId)

    suspend fun setFavoriteTrack(track: Track) {
        dao.updateTrack(track)
    }
}