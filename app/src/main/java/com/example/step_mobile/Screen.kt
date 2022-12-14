package com.example.step_mobile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String, val showBottomBar: Boolean, val showTopBar: Boolean) {
    object HomeScreen: Screen("Home", Icons.Rounded.Home, "home_screen", true, true)
    object SearchScreen: Screen("Search", Icons.Rounded.Search, "search_screen", false, true)
    object PlayScreen: Screen("Workout", Icons.Rounded.PlayArrow, "play_screen", true, true)
    object MyWorkoutsScreen: Screen("Favourites", Icons.Rounded.Favorite, "my_workouts_screen", false, true)
    object WelcomeScreen: Screen("Welcome", Icons.Rounded.Person, "welcome_screen", false, false)
    object LoginScreen: Screen("Login", Icons.Rounded.Person, "login_screen?id={id}", false, false)
    object ViewRoutineScreen: Screen("View Routine", Icons.Rounded.Person, "view_routine_screen", false, false)
    object ProfileScreen: Screen("Profile", Icons.Rounded.Person, "profile_screen", false, false)
    object ShareScreen: Screen("Share", Icons.Rounded.Person, "share_screen?id={id}", false, false)
    object ReviewScreen: Screen("Review", Icons.Rounded.Person, "review_screen", false, false)
    object PlayScreenNT: Screen("Advanced Workout", Icons.Rounded.PlayArrow, "play_screenNT", true, true)
    object SignUpScreen: Screen("Sign Up", Icons.Rounded.PlayArrow, "sign_up_screen", true, true)
    object VerifyScreen: Screen("Verify Email", Icons.Rounded.PlayArrow, "verify_screen", true, true)
    object SearchScreenError: Screen("Search", Icons.Rounded.Search, "search_screen_error", false, true)
}
