package com.example.step_mobile

import android.content.Intent
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.step_mobile.classes.*
import com.example.step_mobile.screens.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyNavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
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

        composable(Screen.VerifyScreen.route){
            VerifyScreen(navController, mainViewModel)
        }

        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController, mainViewModel)
        }

        composable(Screen.ReviewScreen.route){
            ReviewRoutine(navController, mainViewModel)
        }

        composable(Screen.HomeScreen.route) {
            HomeScreen(navController, mainViewModel)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController, mainViewModel )
        }
        composable(Screen.PlayScreen.route) {
            PlayScreen(navController, mainViewModel)
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
            val string = backStackEntry.arguments?.getString("id")
            val num = Integer.parseInt(string)


            ShareScreen(navController, mainViewModel, num)

        }

        composable(Screen.ViewRoutineScreen.route)
        {
            ViewRoutine(navController, mainViewModel)
        }

        composable(Screen.PlayScreenNT.route){
            PlayScreenNT(navController = navController, viewModel = mainViewModel)
        }

        composable(Screen.SearchScreenError.route){
            val snackbarHostState = remember { SnackbarHostState() }
            val snackbarText = stringResource(id = R.string.cannot_routine)
            LaunchedEffect(key1 = true){
                snackbarHostState.showSnackbar(snackbarText)
            }
            SearchScreen(navController = navController, mainViewModel = mainViewModel)
            SnackbarHost(hostState = snackbarHostState)
        }
    }
}
