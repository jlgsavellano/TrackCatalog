package com.appetiser.trackcatalog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appetiser.trackcatalog.data.db.Track
import com.appetiser.trackcatalog.ui.components.CloseButton
import com.appetiser.trackcatalog.ui.components.TrackBanner
import com.appetiser.trackcatalog.ui.components.TrackContent
import com.appetiser.trackcatalog.viewmodel.TrackViewModel

@Composable
fun TrackDetailScreen(
    navController: NavController,
    trackId: Long,
    viewModel: TrackViewModel = hiltViewModel()
) {
    val track by viewModel.track.observeAsState(Track())

    viewModel.getTrack(trackId)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Box {
                TrackBanner(track)
                CloseButton(navController, Modifier.align(Alignment.TopStart))
            }

            TrackContent(track)
        }
    }
}