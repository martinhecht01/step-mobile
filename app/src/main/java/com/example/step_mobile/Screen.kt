package com.example.step_mobile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String, val showBottomBar: Boolean, val showTopBar: Boolean) {
    object HomeScreen: Screen("Home", Icons.Rounded.Home, "home_screen", true, true)
    object SearchScreen: Screen("Search", Icons.Rounded.Search, "search_screen", false, true)
    object PlayScreen: Screen("Workout", Icons.Rounded.PlayArrow, "play_screen", true, true)
    object MyWorkoutsScreen: Screen("Favourites", Icons.Rounded.Favorite, "my_workouts_screen", false, true)
    object WelcomeScreen: Screen("Welcome", Icons.Rounded.Person, "welcome_screen", false, false)
    object LoginScreen: Screen("Login", Icons.Rounded.Person, "login_screen", false, false)
}
