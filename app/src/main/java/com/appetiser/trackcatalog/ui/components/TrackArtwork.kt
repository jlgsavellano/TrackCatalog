package com.appetiser.trackcatalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.appetiser.trackcatalog.data.db.Track

@Composable
fun TrackArtwork(
    track: Track,
    modifier: Modifier = Modifier
        .width(150.dp)
        .height(200.dp),
    corner: Float = 16f
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
            .clip(RoundedCornerShape(corner.dp))
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
                modifier = Modifier.fillMaxSize()
            )
            TrackFavorite(track, Modifier.align(Alignment.TopEnd))
        }
    }
}