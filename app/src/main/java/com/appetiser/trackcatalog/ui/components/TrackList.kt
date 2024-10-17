package com.appetiser.trackcatalog.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appetiser.trackcatalog.data.db.Track

@Composable
fun TrackList(
    tracks: List<Track>,
    navController: NavController
) {
    val genreTracks = tracks.groupBy { track -> track.genre }.toList()

    LazyColumn {
        items(genreTracks) { (genre, tracks) ->
            Column {
                GenreTitle(genre)
                LazyRow {
                    items(tracks) { track ->
                        TrackItem(track, navController)
                    }
                }
            }
        }
    }
}