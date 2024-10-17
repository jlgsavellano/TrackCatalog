package com.appetiser.trackcatalog.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appetiser.trackcatalog.data.db.Track

@Composable
fun TrackContent(track: Track) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Text(
                text = track.name,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        }
        item {
            Text(
                text = track.genre,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Text(
                text = track.description,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
            )
        }
    }
}