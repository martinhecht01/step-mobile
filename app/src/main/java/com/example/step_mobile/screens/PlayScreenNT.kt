package com.example.step_mobile.screens

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.step_mobile.components.ExerciseCard
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.step_mobile.R
import com.example.step_mobile.classes.MainViewModel

@Composable
fun PlayScreenNT(navController: NavController, viewModel: MainViewModel) {
    Surface(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Box {
            val contentPaddingModifier = Modifier.padding(16.dp)
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {  //TODO: DO THIS

                }
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        val state = viewModel.uiState
                        var currentExerciseText by remember { mutableStateOf(state.currentWorkout[0].exercises[0].exercise.name)}
                        var comingUpNext by remember { mutableStateOf("")}
                        if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                            comingUpNext = state.currentWorkout[0].exercises[1].exercise.name
                        else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                            comingUpNext = "Next Cycle = ${state.currentCycles[1].name}"
                        }
                        else
                            comingUpNext = "Nothing! You're almost there!"



                        Card(shape = RoundedCornerShape(15), modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)) {
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Current Exercise:", textAlign = TextAlign.Center, fontSize = 20.sp, modifier = Modifier.padding(top = 50.dp, bottom = 10.dp,start = 10.dp, end = 10.dp))
                                Text(text = currentExerciseText, fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 20.dp, bottom = 50.dp,start = 10.dp, end = 10.dp), fontWeight = FontWeight.Bold)
                            }
                        }
                        Card(shape = RoundedCornerShape(15), modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)) {
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Coming up next:", textAlign = TextAlign.Center,  fontSize = 20.sp, modifier = Modifier.padding(top = 50.dp, bottom = 20.dp,start = 10.dp, end = 10.dp))
                                Text(text = comingUpNext, textAlign = TextAlign.Center, fontSize = 30.sp, modifier = Modifier.padding(top = 10.dp, bottom = 50.dp,start = 10.dp, end = 10.dp), fontWeight = FontWeight.Bold)

                            }
                        }
                        Row(horizontalArrangement = Arrangement.SpaceEvenly){
                            Button(onClick = {
                                if (state.currentExIdx > 0){
                                    state.currentExIdx--
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name
                                    if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                                        comingUpNext = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx+1].exercise.name
                                    else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = "Next Cycle = ${state.currentCycles[state.currentCycleIdx+1].name}"
                                    }
                                    else
                                        comingUpNext = "Nothing! You're almost there!"
                                }
                                else if (state.currentCycleIdx > 0){
                                    state.currentCycleIdx--
                                    state.currentExIdx = state.currentWorkout[state.currentCycleIdx].exercises.size-1
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name

                                    if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = "Next Cycle = ${state.currentCycles[state.currentCycleIdx+1].name}"
                                    }
                                    else
                                        comingUpNext = "Nothing! You're almost there!"
                                }
                            },
                            modifier = Modifier.width(160.dp).padding(end = 30.dp, top = 25.dp)) {
                                Text(text = "Previous",modifier = Modifier.padding(top=30.dp, bottom=30.dp), fontSize = 18.sp)
                            }
                            Button(onClick = {
                                if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1){
                                    state.currentExIdx++
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name
                                    if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                                        comingUpNext = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx+1].exercise.name
                                    else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = "Next Cycle = ${state.currentCycles[state.currentCycleIdx+1].name}"
                                    }
                                    else
                                        comingUpNext = "Nothing! You're almost there!"
                                }
                                else if (state.currentCycleIdx < state.currentWorkout.size-1){
                                    state.currentCycleIdx++
                                    state.currentExIdx = 0
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name
                                    if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                                        comingUpNext = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx+1].exercise.name
                                    else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = "Next Cycle = ${state.currentCycles[state.currentCycleIdx+1].name}"
                                    }
                                    else
                                        comingUpNext = "Nothing! You're almost there!"
                                }
                                else{
                                    navController.navigate("view_routine_screen")
                                    return@Button
                                }

                            },
                                modifier = Modifier.width(160.dp).padding(start = 30.dp, top=25.dp)) {
                                Text(text = "Next",modifier = Modifier.padding(vertical=30.dp), fontSize = 18.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
