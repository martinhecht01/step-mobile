package com.example.step_mobile.screens

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.example.step_mobile.R
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.ui.theme.DarkBlue

@Composable
fun PlayScreenNT(navController: NavController, viewModel: MainViewModel) {
    Surface(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.fondonp), contentDescription = null, contentScale = ContentScale.Crop)
        Box {
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
                        var currentExerciseDetail by remember { mutableStateOf(state.currentWorkout[0].exercises[0].exercise.detail)}
                        var comingUpNext by remember { mutableStateOf("")}
                        var currentCycle by remember { mutableStateOf(state.currentCycles[0].name) }
                        var endFlag by remember { mutableStateOf(false) }
                        var comingUpNextFlag by remember { mutableStateOf(state.currentWorkout[0].exercises.size <= 1) }
                        if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                            comingUpNext = state.currentWorkout[0].exercises[1].exercise.name
                        else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                            comingUpNext = state.currentCycles[1].name
                        }
                        else
                            comingUpNext = "Nothing!"
                        if(viewModel.uiState.currentRoutine != null)
                            ScreenTitle(title = viewModel.uiState.currentRoutine!!.name , showArrow = true, navController = navController)
                        Card(shape = RoundedCornerShape(15),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp)
                                .padding(top = 30.dp, bottom = 15.dp))
                        {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.background(Color.White)) {
                                Text(text = currentCycle, fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp), fontWeight = FontWeight.ExtraBold, color = DarkBlue)
                            }
                        }
                        Card(shape = RoundedCornerShape(15),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp)
                                .padding(bottom = 15.dp))
                        {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.background(DarkBlue)) {
                                Text(text = currentExerciseText, fontSize = 45.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 10.dp, top = 30.dp), fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text(text = currentExerciseDetail, fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 10.dp), color = Color.White)
                            }
                        }
                        Card(shape = RoundedCornerShape(15), modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 25.dp)
                            .padding(horizontal = 25.dp)) {
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Coming up next:", textAlign = TextAlign.Center,  fontSize = 20.sp, modifier = Modifier
                                    .padding(top = 30.dp, bottom = 15.dp)
                                    .padding(horizontal = 15.dp))
                                if(comingUpNextFlag){
                                    Text(text = comingUpNext, textAlign = TextAlign.Center, fontSize = 30.sp, modifier = Modifier
                                        .padding(bottom = 30.dp)
                                        .padding(horizontal = 15.dp), fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
                                } else{
                                    Text(text = comingUpNext, textAlign = TextAlign.Center, fontSize = 30.sp, modifier = Modifier
                                        .padding(bottom = 30.dp)
                                        .padding(horizontal = 15.dp), fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Row(horizontalArrangement = Arrangement.SpaceEvenly){
                            Button(onClick = {
                                comingUpNextFlag = false
                                if (state.currentExIdx > 0){
                                    state.currentExIdx--
                                    currentCycle = state.currentCycles[state.currentCycleIdx].name
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name
                                    currentExerciseDetail = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.detail
                                    if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                                        comingUpNext = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx+1].exercise.name
                                    else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = state.currentCycles[state.currentCycleIdx+1].name
                                        comingUpNextFlag = true
                                    }
                                    else {
                                        comingUpNext = "Nothing!"
                                        endFlag = true
                                    }
                                }
                                else if (state.currentCycleIdx > 0){
                                    state.currentCycleIdx--
                                    state.currentExIdx = state.currentWorkout[state.currentCycleIdx].exercises.size-1
                                    currentCycle = state.currentCycles[state.currentCycleIdx].name
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name
                                    currentExerciseDetail = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.detail

                                    if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = state.currentCycles[state.currentCycleIdx+1].name
                                        comingUpNextFlag = true
                                    }
                                    else {
                                        comingUpNext = "Nothing!"
                                        endFlag = true
                                    }
                                }
                                endFlag = false
                            },

                            modifier = Modifier

                                .width(160.dp)
                                .padding(end = 30.dp, top = 10.dp)
                                .clip(RoundedCornerShape(15.dp))) {
                                Icon(painterResource(R.drawable.fast_rewind), contentDescription = null, tint = DarkBlue)

                            }
                            Button(onClick = {
                                comingUpNextFlag = false
                                if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1){
                                    state.currentExIdx++
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name
                                    currentExerciseDetail = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.detail
                                    if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                                        comingUpNext = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx+1].exercise.name
                                    else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = state.currentCycles[state.currentCycleIdx+1].name
                                        comingUpNextFlag = true
                                    }
                                    else {
                                        comingUpNext = "Nothing!"
                                        endFlag = true
                                    }
                                }
                                else if (state.currentCycleIdx < state.currentWorkout.size-1){
                                    state.currentCycleIdx++
                                    currentCycle = state.currentCycles[state.currentCycleIdx].name
                                    state.currentExIdx = 0
                                    currentExerciseText = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.name
                                    currentExerciseDetail = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx].exercise.detail
                                    if (state.currentExIdx < state.currentWorkout[state.currentCycleIdx].exercises.size-1)
                                        comingUpNext = state.currentWorkout[state.currentCycleIdx].exercises[state.currentExIdx+1].exercise.name
                                    else if (state.currentCycleIdx < state.currentWorkout.size-1) {
                                        comingUpNext = state.currentCycles[state.currentCycleIdx+1].name
                                        comingUpNextFlag = true
                                    }
                                    else {
                                        comingUpNext = "Nothing!"
                                        endFlag = true
                                    }
                                }
                                else{
                                    navController.navigate("review_screen"){
                                        popUpTo("view_routine_screen")
                                    }
                                    return@Button
                                }

                            },
                                modifier = Modifier
                                    .width(160.dp)
                                    .padding(start = 30.dp, top = 10.dp)
                                    .clip(RoundedCornerShape(15.dp))){
                                if(endFlag)
                                    Icon(Icons.Rounded.Done, contentDescription = null, tint = DarkBlue)
                                else
                                    Icon(painterResource(R.drawable.fast_forward), contentDescription = null, tint = DarkBlue)
                            }

                        }

                                Button(
                                    onClick = {
                                        navController.navigate("play_screen")
                                    },
                                    modifier = Modifier
                                        .width(250.dp)
                                        .height(60.dp).padding(top = 20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = DarkBlue,
                                        backgroundColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(40.dp),
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 5.dp,
                                        pressedElevation = 8.dp
                                    )
                                ) {
                                    Text(
                                        "To Timer Screen",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Justify
                                    )
                                }

                        }
                    }
                }
            }
        }
    }
}
