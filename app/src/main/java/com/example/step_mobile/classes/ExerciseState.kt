package com.example.step_mobile.classes

data class ExerciseState (
    val exercises: List<Exercise> = listOf(),
    val isLoading: Boolean = false
)
