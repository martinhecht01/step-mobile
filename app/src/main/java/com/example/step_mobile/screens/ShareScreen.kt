package com.example.step_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.step_mobile.classes.MainViewModel
import com.example.step_mobile.components.ScreenLoader
import com.example.step_mobile.components.ScreenTitle
import com.example.step_mobile.data.model.Name
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.ui.theme.DarkBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShareScreen(navController: NavController, mainViewModel: MainViewModel, id: Int){
    if(mainViewModel.uiState.isAuthenticated) {
        LaunchedEffect(key1 = true) {
            navController.navigate("view_routine_screen")
            mainViewModel.getRoutine(routine.id).invokeOnCompletion {
                mainViewModel.getFullCyclesExercises(routine.id)
            }
        }
    } else{
        navController.navigate("login_screen?id=${id}")
    }
}