package com.example.step_mobile.data.repository


import com.example.step_mobile.data.model.Review
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val routinesMutex = Mutex()
    // Cache of the latest routines got from the network.
    private var routines: List<Routine> = emptyList()
    private var favRoutines: List<Routine> = emptyList()
    private val favMutex = Mutex()



    suspend fun getRoutines(refresh: Boolean = false): List<Routine> {
        if (refresh || routines.isEmpty()) {
            delay(200)
            val result = remoteDataSource.getRoutines()
            // Thread-safe write to latestNews
            routinesMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }
        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine {
        delay(200)
        return remoteDataSource.getRoutine(routineId).asModel()
    }

    suspend fun addRoutine(routine: Routine) : Routine {
        val newRoutine = remoteDataSource.addRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        return newRoutine
    }

    suspend fun modifyRoutine(routine: Routine) : Routine {
        val updatedRoutine = remoteDataSource.modifyRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        return updatedRoutine
    }

    suspend fun deleteRoutine(routineId: Int) {
        remoteDataSource.deleteRoutine(routineId)
        routinesMutex.withLock {
            this.routines = emptyList()
        }
    }

    suspend fun getFavourites(refresh: Boolean = false): List<Routine> {
        delay(200)
        if (refresh || favRoutines.isEmpty()) {
            val result = remoteDataSource.getFavourites()
            // Thread-safe write to latestNews
            favMutex.withLock {
                this.favRoutines = result.content.map { it.asModel() }
            }
        }
        return favMutex.withLock { this.favRoutines }
    }

    suspend fun deleteFromFavourites(routineId: Int){
        remoteDataSource.deleteFromFavourites(routineId)
        favMutex.withLock {
            this.favRoutines = emptyList()
        }
    }

    suspend fun addToFavourites(routineId: Int){
        remoteDataSource.addToFavourites(routineId)
        favMutex.withLock {
            this.favRoutines = emptyList()
        }
    }

    suspend fun reviewRoutine(review : Review, routineId : Int){
        remoteDataSource.reviewRoutine(review.asNetworkModel(), routineId)
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        favMutex.withLock {
            this.favRoutines = emptyList()
        }
    }

}