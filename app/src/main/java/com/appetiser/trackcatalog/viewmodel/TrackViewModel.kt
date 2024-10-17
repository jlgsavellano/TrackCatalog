package com.appetiser.trackcatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appetiser.trackcatalog.data.db.Track
import com.appetiser.trackcatalog.data.repository.TrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Makes this class injectable to be used on Composable UI
 */
@HiltViewModel
class TrackViewModel @Inject constructor(
    private val repository: TrackRepository
): ViewModel() {

    private val _tracks = MutableLiveData<List<Track>>()
    val tracks: LiveData<List<Track>> get() = _tracks

    private val _track = MutableLiveData<Track>()
    val track: LiveData<Track> get() = _track

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
            _track.value = repository.getTrack(track.id)
        }
    }

    fun getTrack(trackId: Long) {
        viewModelScope.launch {
            _track.value = repository.getTrack(trackId)
        }
    }
}