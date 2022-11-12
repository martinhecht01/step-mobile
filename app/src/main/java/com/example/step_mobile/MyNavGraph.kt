package com.example.step_mobile

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.step_mobile.classes.Exercise
import com.example.step_mobile.classes.PlayViewModel
import com.example.step_mobile.classes.Routine
import com.example.step_mobile.classes.RoutineViewModel
import com.example.step_mobile.screens.LoginScreen
import com.example.step_mobile.screens.WelcomeScreen

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.route
    ) {
        var routineViewModel = RoutineViewModel()
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(routineViewModel)
        }
        composable(Screen.PlayScreen.route) {
            PlayScreen(routine = Routine(1,"Press Day", "Rutina de pecho", listOf(Exercise("Pecho", "De pecho"),
                Exercise("Piernas", "de piernas")
            ), 5.0), viewModel = PlayViewModel()
            )
        }
        composable(Screen.MyWorkoutsScreen.route) {
            MyWorkoutsScreen()
        }
        composable(Screen.LoginScreen.route)
        {
            LoginScreen(navController)
        }
        composable(Screen.WelcomeScreen.route)
        {
            WelcomeScreen(navController)
        }

    }
}