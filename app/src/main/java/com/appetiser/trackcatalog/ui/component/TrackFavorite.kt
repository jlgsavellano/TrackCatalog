package com.appetiser.trackcatalog.ui.component


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.appetiser.trackcatalog.data.local.entity.Track
import com.appetiser.trackcatalog.ui.theme.Red

@Composable
fun TrackFavorite(
    track: Track,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onToggle,
        modifier = modifier
    ) {
        if (track.isFavorite) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "track has been marked as favorite",
                tint = Red,
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "track has been unmarked as favorite",
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}