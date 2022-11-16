package com.example.step_mobile.screens

import android.annotation.SuppressLint
import android.app.FragmentManager.BackStackEntry
import android.util.Log
import androidx.annotation.RestrictTo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.step_mobile.data.model.Review
import com.example.step_mobile.ui.theme.PlayGreen
import com.example.step_mobile.ui.theme.StepmobileTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
@Preview
fun ViewRoutine(navController: NavController, mainViewModel: MainViewModel, backStackEntry : NavBackStackEntry) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.fondonp),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    val id = backStackEntry.arguments?.getString("id") ?: ""
    StepmobileTheme() {
        ScreenTitle("")
    }
    if(mainViewModel.uiState.isFetching){
        ScreenLoader()
    } else {
        var routine: Routine?
        routine = null
        if (id.compareTo("-1") == 0)
            routine = mainViewModel.uiState.currentRoutine
        else{
            for (currRout in mainViewModel.uiState.routines)
                if (id.compareTo((currRout.id).toString()) == 0){
                    routine = currRout
                }
        }
        var scope = rememberCoroutineScope()
        if (routine != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                StepmobileTheme() {
                    ScreenTitle(title = routine.name)
                }
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .height(400.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = 10.dp,

                    ) {
                    RoutineInfo(mainViewModel)
                }
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = {
                            scope.launch {
                                //TODO: star review listener (score hardcoded)
                                mainViewModel.reviewRoutine(Review(1, ""), routine.id)
                            }
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.Bottom)
                            .padding(20.dp)
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
    val paddingModifier = Modifier.padding(10.dp)
    val list = (1..9).map { it.toString() }
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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
                                Text(text = "Seems like this routine is empty!", textAlign = TextAlign.Center)
                            }
                        else {
                           mainViewModel.uiState.currentCycles.forEachIndexed() { index, _ ->
                               item {
                                   CycleCard(title = mainViewModel.uiState.currentCycles[index].name)
                               }
                               if (mainViewModel.uiState.currentCycleExercises.isEmpty()) {
                                        item{ Text(text = "Seems like this cycle is empty!", textAlign = TextAlign.Center)}
                               } else {
                                   items(mainViewModel.uiState.currentCycleExercises.size) { index2 ->
                                       ExerciseCardTM(
                                           mainViewModel.uiState.currentCycleExercises[index2].exercise.name,
                                           5
                                       )
                                   }
                               }
                           }
                        }


                    }
                )
        }
}