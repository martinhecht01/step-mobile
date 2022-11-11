package com.example.step_mobile.classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoutineViewModel : ViewModel() {
    var state by mutableStateOf(RoutineState())
        private set

    init{
        viewModelScope.launch{//TODO: aca hay que llamar a la api para llenar las rutinas
            state =state.copy(
                routines = listOf(
                Routine("gayba","feliz"),
                Routine("manu","feliz"),
                Routine("gaybabodybuilder","triste"),
                Routine("aguante","racing"),
                Routine("locelso","abandonaste")),
                isLoading = false
            )
        }
    }

}