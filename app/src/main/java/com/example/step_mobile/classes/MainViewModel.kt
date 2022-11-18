package com.example.step_mobile.classes


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.step_mobile.data.model.*
import com.example.step_mobile.data.repository.*
import com.example.step_mobile.util.SessionManager
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
    suspend fun login(username: String, password: String) = viewModelScope.launch {

            uiState = uiState.copy(
                isFetching = true,
                message = null,
                currentUser = null
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

    fun signUp(data: SignUp) = viewModelScope.launch {

        uiState = uiState.copy(
            isFetching = true,
            message = null,
        )

        runCatching {
            userRepository.signUp(data)
        }.onSuccess {
            uiState = uiState.copy(
                isFetching = false,
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }

    }

    fun verify(data: Verify) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null,
        )

        runCatching {
            userRepository.verify(data)
        }.onSuccess {
            uiState = uiState.copy(
                isFetching = false,
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
                currentRoutine = null,
                isFetching = false)
        }
    }

    fun getTopRoutine() : Routine?{
        var routine: Routine? = null
        var maxRating = -1;
        for(curr in uiState.routines){
            if((curr.score ?: 0) > maxRating){
                routine = curr
                maxRating = (curr.score ?: 0)
            }
        }
        return routine;
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
                currentCycles = response,
            )
            response.forEach{elem ->
                getCycleExercises(elem.id)
            }


        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    private suspend fun getCycleExercises(cycleId: Int) {
        runCatching {
            cycleExerciseRepository.getCycleExercises(cycleId)
        }.onSuccess { response ->
            Log.d("exer", response.toString())
            uiState = uiState.copy(
                currentWorkout = uiState.currentWorkout.plus(FullCycle(cycleId,response))
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message)
        }
    }

    fun getFullCyclesExercises(routineId: Int) = viewModelScope.launch{
        uiState = uiState.copy(
            isFetching = true,
            currentWorkout = listOf(),
            currentCycles = listOf(),
            message = null
        )
        runCatching {
            cycleRepository.getCycles(routineId)
        }.onSuccess { response ->
            Log.d("response", response.toString())
            uiState = uiState.copy(
                currentCycles = response,
            )
            response.forEach{elem ->
                getCycleExercises(elem.id)
            }
            uiState = uiState.copy(
                isFetching = false
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }
}