package com.appetiser.trackcatalog.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appetiser.trackcatalog.data.local.entity.Track
import com.appetiser.trackcatalog.ui.component.CloseButton
import com.appetiser.trackcatalog.ui.component.TrackBanner
import com.appetiser.trackcatalog.ui.component.TrackContent

@Composable
fun TrackDetailScreen(
    navController: NavController,
    trackId: Long,
    viewModel: TrackDetailViewModel = hiltViewModel()
) {
    val track by viewModel.track.observeAsState(Track())

    LaunchedEffect(Unit) {
        viewModel.getTrack(trackId)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Box {
                TrackBanner(
                    track = track,
                    onToggleFavorite = {
                        viewModel.setFavoriteTrack(track.copy(isFavorite = !track.isFavorite))
                    }
                )
                CloseButton(navController, Modifier.align(Alignment.TopStart))
            }

            TrackContent(track)
        }
    }
}