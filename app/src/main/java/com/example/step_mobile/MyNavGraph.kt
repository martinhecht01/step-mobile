package com.example.step_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.step_mobile.classes.*
import com.example.step_mobile.screens.LoginScreen
import com.example.step_mobile.screens.ViewRoutine
import com.example.step_mobile.screens.WelcomeScreen

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.route
    ) {
        var routineViewModel = RoutineViewModel(navController)
        composable(Screen.HomeScreen.route) {
            HomeScreen(routineViewModel)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(routineViewModel, navController)
        }
        composable(Screen.PlayScreen.route) {
            PlayScreen(routine = Routine(1,"Press Day", "Rutina de pecho", listOf(
                Cycle("Pecho", listOf<Exercise>(Exercise("Piernas", "de piernas")),
                )
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
        composable(Screen.ViewRoutineScreen.route){
            val arg = it.arguments?.getString("id") ?: "-1"
            val id = Integer.parseInt(arg)
            //Aca podes hacer manejo de error
            //if(-1) -> errorScreen
            ViewRoutine(navController, id , routineViewModel)
        }

    }
}