package com.example.step_mobile.data.network

import com.example.step_mobile.data.network.api.ApiCycleExerciseService
import com.example.step_mobile.data.network.api.ApiCycleService
import com.example.step_mobile.data.network.model.NetworkCycle
import com.example.step_mobile.data.network.model.NetworkCycleExercise
import com.example.step_mobile.data.network.model.NetworkPagedContent

class CycleExerciseRemoteDataSource(
    private val apiCycleExerciseService: ApiCycleExerciseService
) : RemoteDataSource() {

    suspend fun getCycleExercises(cycleId: Int) : NetworkPagedContent<NetworkCycleExercise> {
        return handleApiResponse {
            apiCycleExerciseService.getCycleExercises(cycleId)
        }
    }

}