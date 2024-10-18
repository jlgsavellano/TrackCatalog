package com.appetiser.trackcatalog.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LastVisitedTitle(
    lastVisit: Date?,
    modifier: Modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
) {
    val formatter = SimpleDateFormat("MMM. dd, yyyy 'at' HH:mm", Locale.getDefault())
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            text = lastVisit?.let { date ->
                "Visited Last ${formatter.format(date)}"
            } ?: "No Known Last Visit",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}