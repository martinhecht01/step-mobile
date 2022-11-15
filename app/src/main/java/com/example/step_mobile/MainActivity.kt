package com.example.step_mobile

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.step_mobile.ui.theme.BottomNavigationTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.ui.theme.StepmobileTheme
import com.example.step_mobile.util.getViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepmobileTheme{}
            BottomNavigationTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val viewModel = viewModel<MainViewModel>(factory = getViewModelFactory())
                val showBottomBar = when (viewModel.uiState.isAuthenticated){
                    false -> false
                    else -> true
                }

                Scaffold(
                    bottomBar = { if(showBottomBar) BottomBar(navController = navController, viewModel(factory = getViewModelFactory())) }
                ) {
                    MyNavGraph(navController = navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController, viewModel: MainViewModel) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.SearchScreen,
        Screen.MyWorkoutsScreen,
    )
    var scope = rememberCoroutineScope()
    if (true) {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = Color.DarkGray,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    //label = {Text(text = item.title, color = Color.DarkGray)},
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = {
                        scope.launch {

                            // search_screen
                            if(item.route == Screen.SearchScreen.route) {
                                viewModel.getRoutines()
                                viewModel.getFavourites()
                            }

                            //my_workouts_screen
                            if(item.route == Screen.MyWorkoutsScreen.route)
                                viewModel.getFavourites()

                            Log.d("routines", viewModel.uiState.routines.size.toString())
                        }
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    //Saveo el estado de como tengo mi pantalla actual?
                                    saveState = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
            var scope = rememberCoroutineScope()
            BottomNavigationItem(
                onClick = {
                    scope.launch {
                        viewModel.logout()
                        if (!viewModel.uiState.isAuthenticated) {
                            navController.navigate("welcome_screen") {
                                popUpTo(0)
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        painterResource(R.drawable.ic_logout),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.DarkGray
                    )
                },
                alwaysShowLabel = true,
                selected = true
            )
            }
    }
}