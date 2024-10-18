package com.appetiser.trackcatalog.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.appetiser.trackcatalog.data.local.entity.Track

@Composable
fun TrackBanner(
    track: Track,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(track.artwork)
            .scale(Scale.FIT)
            .crossfade(true)
            .build()
    )

    Surface(
        modifier = modifier
            .background(Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(MaterialTheme.colorScheme.onBackground, Color.Gray),
                        radius = 200f
                    )
                )
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize(),
            )
            TrackFavorite(
                track = track,
                onToggle = onToggleFavorite,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}