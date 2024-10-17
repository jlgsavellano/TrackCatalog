package com.appetiser.trackcatalog.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.appetiser.trackcatalog.data.db.Track
import java.util.Locale

@Composable
fun TrackTitle(
    track: Track,
    modifier: Modifier = Modifier
        .width(150.dp)
        .padding(top = 8.dp, bottom = 8.dp)
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = track.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(80.dp)
        )
        Text(
            text = "${track.currency} ${String.format(Locale.getDefault(), "%.2f", track.price)}",
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(80.dp)
        )
    }
}