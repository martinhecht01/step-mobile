package com.example.step_mobile.screens

import StarBar
import android.annotation.SuppressLint
import android.app.FragmentManager.BackStackEntry
import android.util.Log
import androidx.annotation.RestrictTo
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.navDeepLink
import com.example.step_mobile.R
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.data.model.Routine

import com.example.step_mobile.components.CycleCard
import com.example.step_mobile.components.ExerciseCardTM
import com.example.step_mobile.components.ScreenLoader
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.data.model.Name
import com.example.step_mobile.data.model.Review
import com.example.step_mobile.ui.theme.DarkBlue
import com.example.step_mobile.ui.theme.PlayGreen
import com.example.step_mobile.ui.theme.StepmobileTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ViewRoutine(navController: NavController, mainViewModel: MainViewModel) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.fondonp),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    StepmobileTheme() {
        ScreenTitle("", true, navController)
    }
    if(mainViewModel.uiState.isFetching){
        ScreenLoader()
    } else {
        var routine = mainViewModel.uiState.currentRoutine
        if (routine != null) {
            var showArrow = mainViewModel.uiState.isAuthenticated
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                StepmobileTheme() {
                    ScreenTitle(title = routine.name, showArrow, navController)
                }
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .height(400.dp)
                        .fillMaxWidth(),

                    shape = RoundedCornerShape(20.dp),
                    elevation = 10.dp,

                    ) {
                    RoutineInfo(mainViewModel)
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
                        Button(
                            onClick = {
                                if (mainViewModel.uiState.currentWorkout.isEmpty()){
                                    navController.navigate("search_screen")
                                    return@Button
                                }
                                for (cycle in mainViewModel.uiState.currentWorkout){
                                    if (cycle.exercises.isEmpty()){
                                        navController.navigate("search_screen") //TODO: llevar a otra pantalla donde indique error?
                                        return@Button
                                    }
                                }
                                mainViewModel.uiState.currentCycleIdx = 0
                                mainViewModel.uiState.currentExIdx = 0
                                navController.navigate("play_screenNT")
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .size(60.dp),
                            shape = RoundedCornerShape(100),
                            elevation = ButtonDefaults.elevation(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PlayGreen,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
                        }
                        Text(text = stringResource(id = R.string.play), fontSize = 15.sp, color = Color.White)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
                        Button(
                            onClick = {
                                navController.navigate("review_screen")
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .size(60.dp),
                            shape = RoundedCornerShape(100),
                            elevation = ButtonDefaults.elevation(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = DarkBlue
                            )
                        ) {
                            Icon(painterResource(id = R.drawable.ic_star), contentDescription = null)
                        }
                        Text(text = stringResource(id = R.string.review), fontSize = 15.sp, color = Color.White)
                    }
                }
            }
        } else {
            //TODO: Error management? Tirar error screen?
            navController.navigate("search_screen") {
                popUpTo("search_screen")
            }
        }
    }
}

@Composable
fun RoutineInfo(mainViewModel: MainViewModel){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            LazyVerticalGrid(
                columns = GridCells.Adaptive(200.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                   if (mainViewModel.uiState.currentCycles.isEmpty())
                        item{
                            Text(stringResource(id = R.string.empty_routine), textAlign = TextAlign.Center)
                        }
                   else {
                       mainViewModel.uiState.currentCycles.forEachIndexed() { index, _ ->
                           item {
                               CycleCard(title = mainViewModel.uiState.currentCycles[index].name)
                           }
                           if (mainViewModel.uiState.currentWorkout[index].exercises.isEmpty()) {
                                    item{ Text(stringResource(id = R.string.empty_cycle), textAlign = TextAlign.Center)}
                           } else {
                               items(mainViewModel.uiState.currentWorkout[index].exercises.size) { index2 ->
                                   ExerciseCardTM(
                                       mainViewModel.uiState.currentWorkout[index].exercises[index2].exercise.name,
                                       mainViewModel.uiState.currentWorkout[index].exercises[index2].repetitions
                                   )
                               }
                           }
                       }
                   }
                }
            )
        }
}