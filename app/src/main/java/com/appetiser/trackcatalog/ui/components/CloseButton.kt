package com.appetiser.trackcatalog.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CloseButton(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = {
            navController.popBackStack()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "track details screen close",
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}