package com.example.step_mobile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.step_mobile.classes.*
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.screens.LoginScreen
import com.example.step_mobile.screens.ViewRoutine
import com.example.step_mobile.screens.WelcomeScreen
import com.example.step_mobile.util.getViewModelFactory
import kotlinx.coroutines.launch

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

        composable(Screen.HomeScreen.route) {
            HomeScreen(mainViewModel)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController, mainViewModel )
        }
        composable(Screen.PlayScreen.route) {
//            PlayScreen(routine = Routine(1,"Press Day", "Rutina de pecho", listOf( TODO:TIENE QUE RECIBIR MAINviewmodel (creo)
//                Cycle("Pecho", listOf<Exercise>(Exercise("Piernas", "de piernas")),
//                )
//            ), 5.0), viewModel = PlayViewModel()
//            )
        }
        composable(Screen.MyWorkoutsScreen.route) {
            MyWorkoutsScreen()
        }
        composable(Screen.LoginScreen.route)
        {
            LoginScreen(navController, mainViewModel)
        }
        composable(Screen.WelcomeScreen.route)
        {
            WelcomeScreen(navController, mainViewModel)
        }
        composable(Screen.ViewRoutineScreen.route)
        {
            val arg = it.arguments?.getString("id") ?: "-1"
            val id = Integer.parseInt(arg)
            //Aca podes hacer manejo de error
            //if(-1) -> errorScreen
//            ViewRoutine(navController, id , routineViewModel) //TODO: SE ROMPIO CON LA NUEVA IMPLEMENTACION
        }

    }

}
