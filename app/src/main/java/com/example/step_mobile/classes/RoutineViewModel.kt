package com.example.step_mobile.classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.step_mobile.repositories.RoutineRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoutineViewModel(
    private val repository: RoutineRepository
) : ViewModel() {
    var state by mutableStateOf(RoutineState())
        private set

    init{
        viewModelScope.launch{//TODO: aca hay que llamar a la api para llenar las rutinas
            state = state.copy(
                isLoading = true
            )
            delay(2000)
            state =state.copy(
                routines = listOf(
                Routine("gayba","feliz"),
                Routine("manu","feliz"),
                Routine("gaybabodybuilder","triste"),
                Routine("aguante","racing"),
                Routine("locelso","abandonaste")),
                //routines = repository.getRoutines(), // Para cuando podamos conectar la api
                isLoading = false
            )
        }
    }

}