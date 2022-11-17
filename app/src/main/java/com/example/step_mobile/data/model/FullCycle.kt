package com.example.step_mobile.data.model

data class FullCycle(
    var cycleId : Int,
    var exercises : List<CycleExercise> =listOf()
)