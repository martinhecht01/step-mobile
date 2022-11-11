package com.example.step_mobile.classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.step_mobile.repositories.ExerciseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayViewModel () : ViewModel() {

    var exerciseIndex by mutableStateOf(0)
        private set

    init {
        viewModelScope.launch {
            exerciseIndex = 0
        }
    }

    fun incIndex(){
        exerciseIndex++;
    }

    fun currentIndex(): Int {
        return exerciseIndex
    }

}