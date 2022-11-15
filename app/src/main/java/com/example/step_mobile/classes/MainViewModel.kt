package com.example.step_mobile.classes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.step_mobile.data.model.Name
import com.example.step_mobile.data.model.Review
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.util.SessionManager
import com.example.step_mobile.data.model.Sport
import com.example.step_mobile.data.repository.*
import com.example.step_mobile.util.getViewModelFactory

import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository,
    private val sportRepository: SportRepository,
    private val cycleRepository: CycleRepository,
    private val cycleExerciseRepository: CycleExerciseRepository

    ) : ViewModel() {

    var uiState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set
    var lastGetSportsTimestamp = 0

    // --------------------- USER ----------------------
    //TODO try with suspend
    suspend fun login(username: String, password: String) = viewModelScope.launch {

            uiState = uiState.copy(
                isFetching = true,
                message = null,
            )

            runCatching {
                userRepository.login(username, password)
            }.onSuccess { response ->
                uiState = uiState.copy(
                    isFetching = false,
                    isAuthenticated = true
                )
            }.onFailure { e ->
                // Handle the error and notify the UI when appropriate.
                uiState = uiState.copy(
                    message = e.message,
                    isFetching = false)
            }

    }

    fun logout() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.logout()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                isAuthenticated = false,
                currentUser = null,
                currentSport = null,
                sports = listOf()
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.getCurrentUser(uiState.currentUser == null)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentUser = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    suspend fun modifyUser(newName : Name) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.modifyUser(newName)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentUser = null
            )
            getCurrentUser()
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    // -------------------- SPORTS ----------------------

    fun getSports(sport : Sport) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.getSports(true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                sports = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getSport(sportId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.getSport(sportId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun addOrModifySport(sport:  Sport) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            if (sport.id == null)
                sportRepository.addSport(sport)
            else
                sportRepository.modifySport(sport)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = response,
                //TODO: MODIFY sports state
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun deleteSport(sportId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.deleteSport(sportId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = null,
                //TODO: REMOVE SPORT FROM STATE
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }


    //-------------------- ROUTINES -------------------------

    fun getRoutines() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutines(false)
        }.onSuccess { response ->
            Log.d("response", response.toString())
            uiState = uiState.copy(
                isFetching = false,
                routines = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }


    fun getRoutine(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutine(routineId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentRoutine = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    suspend fun reviewRoutine(review: Review, id : Int) = viewModelScope.launch{
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.reviewRoutine(review, id)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = listOf(),
                favRoutines = listOf()
            )
            getRoutines()
            getFavourites()

        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }


    // ---------------------- FAVOURITES --------------------

    fun deleteFromFavourites(id: Int) = viewModelScope.launch{
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.deleteFromFavourites(id)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,

                //la vacio para evitar conflictos
                favRoutines = listOf(),
            )
            //el delete la vacio y ahora la llenamos de nuevo
            getFavourites()
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun addToFavourites(id: Int) = viewModelScope.launch{
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.addToFavourites(id)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,

                //la vacio para evitar conflictos
                favRoutines = listOf(),
            )
            //el delete la vacio y ahora la llenamos de nuevo
            getFavourites()
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun isFavourite(id: Int): Boolean{
        for(routine in uiState.favRoutines){
            if (routine.id == id)
                return true
        }
        return false
    }

    fun getFavourites() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getFavourites(false)
        }.onSuccess { response ->
            Log.d("response", response.toString())
            uiState = uiState.copy(
                isFetching = false,
                favRoutines = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }



    //----------------- CYCLE AND CYCLEEXERCISE ---------------------

    fun getCycles(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            cycleRepository.getCycles(routineId)
        }.onSuccess { response ->
            Log.d("response", response.toString())
            uiState = uiState.copy(
                isFetching = false,
                currentCycles = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getCycleExercises(cycleId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            cycleExerciseRepository.getCycleExercises(cycleId)
        }.onSuccess { response ->
            Log.d("response", response.toString())
            uiState = uiState.copy(
                isFetching = false,
                currentCycleExercises = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }



}