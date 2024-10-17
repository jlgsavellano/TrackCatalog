package com.appetiser.trackcatalog.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appetiser.trackcatalog.ui.theme.TrackCatalogTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Using Jetpack Compose for designing UI
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrackCatalogTheme {
                NavHostComponent()
            }
        }
    }
}

@Composable
fun NavHostComponent() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "trackCatalog") {
        composable("trackCatalog") {
            TrackCatalogScreen(navController)
        }
        composable("trackDetail/{trackId}") { backStackEntry ->
            backStackEntry.arguments?.getString("trackId")?.toLongOrNull()?.let { trackId ->
                TrackDetailScreen(navController, trackId)
            }
        }
    }
}

