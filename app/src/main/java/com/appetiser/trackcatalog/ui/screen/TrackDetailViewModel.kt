package com.appetiser.trackcatalog.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appetiser.trackcatalog.data.local.entity.Track
import com.appetiser.trackcatalog.data.repository.TrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Makes this class injectable to be used on TrackDetailScreen
 */
@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val repository: TrackRepository
): ViewModel() {

    private val _track = MutableLiveData<Track>()
    val track: LiveData<Track> get() = _track

    fun setFavoriteTrack(track: Track) {
        viewModelScope.launch {
            repository.setFavoriteTrack(track)
            _track.value = repository.getTrack(track.id)
        }
    }

    fun getTrack(trackId: Long) {
        viewModelScope.launch {
            _track.value = repository.getTrack(trackId)
        }
    }
}