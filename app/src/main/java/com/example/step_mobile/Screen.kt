package com.example.step_mobile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String) {
    object HomeScreen: Screen("Home", Icons.Filled.Home, "home_screen")
    object SearchScreen: Screen("Search", Icons.Filled.Search, "search_screen")
    object PlayScreen: Screen("Workout", Icons.Filled.PlayArrow, "play_screen")
    object MyWorkoutsScreen: Screen("My Workouts", Icons.Filled.Star, "my_workouts_screen")
}