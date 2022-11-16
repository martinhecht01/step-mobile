package com.example.step_mobile

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.step_mobile.classes.*
import com.example.step_mobile.screens.*

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

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController, mainViewModel)
        }

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
            MyWorkoutsScreen(navController, mainViewModel)
        }
        composable(Screen.LoginScreen.route,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            ))
        {backStackEntry ->
            var string = backStackEntry.arguments?.getString("id")
            var num = Integer.parseInt(string ?: "-1")
            LoginScreen(navController, mainViewModel, num)
        }
        composable(Screen.WelcomeScreen.route)
        {
            WelcomeScreen(navController, mainViewModel)
        }
        val uri = "https://www.stepapp.me"
        composable(
            Screen.ShareScreen.route,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })
        ) { backStackEntry ->
            var string = backStackEntry.arguments?.getString("id")
            var num = Integer.parseInt(string)
            mainViewModel.getRoutines()
            ShareScreen(navController, mainViewModel, num)
        }

        composable(Screen.ViewRoutineScreen.route)
        {
            ViewRoutine(navController, mainViewModel)
        }


    }

}
