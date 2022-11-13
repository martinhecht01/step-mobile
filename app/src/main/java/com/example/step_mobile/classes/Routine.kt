package com.example.step_mobile.classes


data class Routine(
    val id: Int,
    val title: String,
    val description: String,
    val cycles: List<Cycle>,
    val rating: Double
)