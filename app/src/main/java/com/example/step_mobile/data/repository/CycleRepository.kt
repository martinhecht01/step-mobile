package com.example.step_mobile.data.repository


import com.example.step_mobile.data.model.Cycle
import com.example.step_mobile.data.network.CycleRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CycleRepository(
    private val remoteDataSource: CycleRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val cyclesMutex = Mutex()
    // Cache of the latest routines got from the network.
    private var cycles: List<Cycle> = emptyList()

    suspend fun getCycles(routineId: Int): List<Cycle> {
        if (cycles.isEmpty()) {
            val result = remoteDataSource.getCycles(routineId)
            // Thread-safe write to latestNews
            cyclesMutex.withLock {
                this.cycles = result.content.map { it.asModel() }
            }
        }
        return cyclesMutex.withLock { this.cycles }
    }
}