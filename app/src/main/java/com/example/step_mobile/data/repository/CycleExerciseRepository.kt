package com.example.step_mobile.data.repository

import com.example.step_mobile.data.model.Cycle
import com.example.step_mobile.data.model.CycleExercise
import com.example.step_mobile.data.network.CycleExerciseRemoteDataSource
import com.example.step_mobile.data.network.CycleRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CycleExerciseRepository(
    private val remoteDataSource: CycleExerciseRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val cyclesMutex = Mutex()
    // Cache of the latest routines got from the network.
    private var cycleExercises: List<CycleExercise> = emptyList()

    suspend fun getCycleExercises(cycleId: Int): List<CycleExercise> {
        if (cycleExercises.isEmpty()) {
            val result = remoteDataSource.getCycleExercises(cycleId)
            // Thread-safe write to latestNews
            cyclesMutex.withLock {
                this.cycleExercises = result.content.map { it.asModel() }
            }
        }
        return cyclesMutex.withLock { this.cycleExercises }
    }
}
