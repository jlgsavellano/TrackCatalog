package com.appetiser.trackcatalog.ui.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.appetiser.trackcatalog.data.db.Track
import com.appetiser.trackcatalog.ui.theme.Red
import com.appetiser.trackcatalog.viewmodel.TrackViewModel

@Composable
fun TrackFavorite(
    track: Track,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = hiltViewModel()
) {
    IconButton(
        onClick = {
            viewModel.setFavoriteTrack(track.copy(isFavorite = !track.isFavorite))
        },
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