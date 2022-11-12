package com.example.step_mobile.classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class RoutineState (
    val routines: List<Routine> = listOf(),
    val isLoading: Boolean = false
)//TODO:coleccion para mensajes de error (string o lista de strings)


