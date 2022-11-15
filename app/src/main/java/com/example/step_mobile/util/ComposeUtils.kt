package com.example.step_mobile.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.step_mobile.StepApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as StepApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val sportRepository = application.sportRepository
    val routineRepository = application.routineRepository
    val cycleRepository = application.cycleRepository
    val cycleExerciseRepository = application.cycleExerciseRepository
    return ViewModelFactory(sessionManager, userRepository, sportRepository, routineRepository,cycleRepository,cycleExerciseRepository, LocalSavedStateRegistryOwner.current, defaultArgs)
}