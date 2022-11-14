package com.example.step_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.step_mobile.ui.theme.BottomNavigationTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.step_mobile.classes.MainViewModel
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
                val viewModel = viewModel<MainViewModel>(factory = getViewModelFactory())
                Scaffold(
                    bottomBar = { BottomBar(navController = navController, viewModel(factory = getViewModelFactory())) }
                ) {
                    MyNavGraph(navController = navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController, viewModel : MainViewModel) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.SearchScreen,
        Screen.MyWorkoutsScreen,
    )
    var scope = rememberCoroutineScope()
    if(true) {
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
                            viewModel.getRoutines()
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
                            Log.d("routines", viewModel.uiState.routines.size.toString())
                        }
                    }
                )
            }
        }
    }
}