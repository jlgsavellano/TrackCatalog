package com.appetiser.trackcatalog.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appetiser.trackcatalog.data.local.entity.Track
import com.appetiser.trackcatalog.ui.component.SearchBar
import com.appetiser.trackcatalog.ui.component.TrackList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@Composable
fun TrackCatalogScreen(
    navController: NavController,
    viewModel: TrackCatalogViewModel = hiltViewModel()
) {
    val tracks by viewModel.tracks.observeAsState(emptyList())
    var filteredTracks by remember { mutableStateOf(emptyList<Track>()) }
    var searchQuery by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    viewModel.initializeTracks("star")

    LaunchedEffect(tracks, searchQuery) {
        coroutineScope {
            if (searchQuery.isNotEmpty())
                delay(1000)

            filteredTracks = tracks.filter { track ->
                track.name.contains(searchQuery, ignoreCase = true) or
                        track.genre.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
    ) {

        Column {
            SearchBar(
                query = searchQuery,
                onQueryChange = { query ->
                    searchQuery = query
                },
                onFocusChange = { state ->
                    isFocused = state.isFocused
                }
            )
            if (!isFocused or searchQuery.isNotEmpty()) {
                TrackList(
                    tracks = filteredTracks,
                    onToggleFavorite = { track ->
                        viewModel.setFavoriteTrack(track.copy(isFavorite = !track.isFavorite))
                    },
                    navController = navController
                )
            }
        }
    }
}
