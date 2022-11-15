package com.example.step_mobile.classes

import com.example.step_mobile.data.model.Exercise

data class ExerciseState (
    val exercises: List<Exercise> = listOf(),
    val isLoading: Boolean = false
)
