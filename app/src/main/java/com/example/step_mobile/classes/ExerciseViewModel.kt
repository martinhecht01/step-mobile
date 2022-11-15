package com.example.step_mobile.classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.step_mobile.data.model.Exercise
import com.example.step_mobile.repositories.ExerciseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExerciseViewModel {
//    private val repository: ExerciseRepository
//) : ViewModel() {
//
//    var state by mutableStateOf(ExerciseState())
//        private set
//
//    init{
//        viewModelScope.launch{//TODO: aca hay que llamar a la api para llenar las rutinas
//            delay(2000)
//            state =state.copy(
//                exercises = listOf(
//                    Exercise("gayba","feliz"),
//                    Exercise("manu","feliz"),
//                    Exercise("gaybabodybuilder","triste"),
//                    Exercise("aguante","racing"),
//                    Exercise("locelso","abandonaste")
//                ),
//                //exercises = repository.getRoutines(), // Para cuando podamos conectar la api
//            )
//        }
//    }

}