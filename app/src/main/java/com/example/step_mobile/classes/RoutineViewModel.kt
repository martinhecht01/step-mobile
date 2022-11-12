package com.example.step_mobile.classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.step_mobile.repositories.RoutineRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//class RoutineViewModel(private val repository: RoutineRepository) : ViewModel() {
class RoutineViewModel(navController: NavController) : ViewModel() {

    var state by mutableStateOf(RoutineState())
        private set

    val controller = navController

    init{
        viewModelScope.launch{//TODO: aca hay que llamar a la api para llenar las rutinas
            state = state.copy(
                routines = listOf(
                Routine(1,"Pechardo", "Tetas", listOf(), 4.0),
                Routine(2,"Piernas de tero", "Soy veloz", listOf(), 3.0),
                Routine(3,"Hombros de acero", "La mole moli", listOf(), 5.0),
                Routine(4,"Gayba mode", "Gayba esta durp", listOf(), 4.5),
                Routine(5,"Tobi pollito", "Tobi esta duro", listOf(), 4.0)
                ),
                isLoading = false
            )
        }
    }

    //Evento que apretan ua rutina
    fun onRoutineClicked(id: Int){
        controller.navigate("view_routine_screen/${id}")
    }

    fun getIndexWithId(id: Int): Int {
        for((index, routine) in state.routines.withIndex()){
            if(routine.id == id)
                return index
        }
        return -1
    }

    fun getIndexTopRoutine(): Int{
        var topRating = -1.0;
        var topIndex = -1;
        for((index, routine) in state.routines.withIndex()){
            if(routine.rating > topRating){
                topRating = routine.rating
                topIndex = index
            }
        }
        return topIndex
    }

}