package com.appetiser.trackcatalog.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appetiser.trackcatalog.data.local.entity.Track
import com.appetiser.trackcatalog.data.repository.TrackRepository
import com.appetiser.trackcatalog.utils.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

/**
 * Makes this class injectable to be used on TrackCatalogScreen
 */
@HiltViewModel
class TrackCatalogViewModel @Inject constructor(
    private val repository: TrackRepository,
    private val dateUtil: DateUtil
): ViewModel() {

    private val _tracks = MutableLiveData<List<Track>>()
    val tracks: LiveData<List<Track>> get() = _tracks

    fun initializeTracks(term: String, country: String = "au") {
        viewModelScope.launch {
            repository.searchTracks(term, country)
            _tracks.value = repository.getAllTracks(country)
        }
    }

    fun setFavoriteTrack(track: Track) {
        viewModelScope.launch {
            repository.setFavoriteTrack(track)
            _tracks.value = repository.getAllTracks(track.country)
        }
    }

    fun updateLastVisited() {
        dateUtil.lastVisit = Date()
    }

    fun getLastVisited(): Date? = dateUtil.lastVisit
}