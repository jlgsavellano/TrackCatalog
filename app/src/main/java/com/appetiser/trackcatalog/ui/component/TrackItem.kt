package com.appetiser.trackcatalog.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appetiser.trackcatalog.data.local.entity.Track

@Composable
fun TrackItem(
    track: Track,
    onToggleFavorite: (Track) -> Unit,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .clickable { navController.navigate("trackDetail/${track.id}") }
    ) {
        TrackArtwork(
            track = track,
            onToggleFavorite = { onToggleFavorite(track) }
        )
        TrackTitle(track)
    }
}