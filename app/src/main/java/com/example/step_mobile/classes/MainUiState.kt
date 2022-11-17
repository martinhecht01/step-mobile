package com.example.step_mobile.classes

import com.example.step_mobile.data.model.*

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val sports: List<Sport> = listOf(),
    val currentSport: Sport? = null,
    val routines: List<Routine> = listOf(),
    val favRoutines: List<Routine> = listOf(),
    val currentRoutine: Routine? = null,
    val message: String? = null,
    val currentCycles: List<Cycle> = listOf(),
    val currentWorkout: List<FullCycle> = listOf(),
    var currentCycleIdx: Int = 0,
    var currentExIdx: Int = 0
)

val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport
