package com.example.step_mobile

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.step_mobile.classes.*
import com.example.step_mobile.screens.LoginScreen
import com.example.step_mobile.screens.ViewRoutine
import com.example.step_mobile.screens.WelcomeScreen
import com.example.step_mobile.util.getViewModelFactory

@Composable
fun MyNavGraph(navController: NavHostController, mainViewModel: MainViewModel) { //TODO: para pasar viewmodels: min 2:14 de la clase o
// TODO: screeenX(viewModel : MainViewModel = factory = getViewModelFactory())) en la declaracion PS:que facha que es manu
    var start = Screen.WelcomeScreen.route
    if(mainViewModel.uiState.isAuthenticated)
        start = Screen.HomeScreen.route
    NavHost(
        navController = navController,
        startDestination = start
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
            LoginScreen(navController,   viewModel(factory = getViewModelFactory()))
        }
        composable(Screen.WelcomeScreen.route)
        {
            WelcomeScreen(navController, viewModel(factory = getViewModelFactory()))
        }
        composable(Screen.ViewRoutineScreen.route)
        {
            val arg = it.arguments?.getString("id") ?: "-1"
            val id = Integer.parseInt(arg)
            ViewRoutine(navController, id , routineViewModel)
        }

    }

}
