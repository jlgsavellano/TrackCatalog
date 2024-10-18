package com.appetiser.trackcatalog.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.appetiser.trackcatalog.data.local.entity.Track
import java.util.Date

@Composable
fun TrackList(
    lastVisited: Date?,
    tracks: List<Track>,
    onToggleFavorite: (Track) -> Unit,
    navController: NavController
) {
    val genreTracks = tracks.groupBy { track -> track.genre }.toList()
    LazyColumn {
        item {
            LastVisitedTitle(lastVisit = lastVisited)
        }
        items(genreTracks) { (genre, tracks) ->
            Column {
                GenreTitle(genre)
                LazyRow {
                    items(tracks) { track ->
                        TrackItem(
                            track = track,
                            onToggleFavorite = { onToggleFavorite(track) },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}