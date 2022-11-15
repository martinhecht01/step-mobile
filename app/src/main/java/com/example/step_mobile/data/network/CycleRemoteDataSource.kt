package com.example.step_mobile.data.network

import com.example.step_mobile.data.network.api.ApiCycleService
import com.example.step_mobile.data.network.model.NetworkCycle
import com.example.step_mobile.data.network.model.NetworkPagedContent

class CycleRemoteDataSource(
    private val apiCycleService: ApiCycleService
) : RemoteDataSource() {

    suspend fun getCycles(routineId: Int) : NetworkPagedContent<NetworkCycle> {
        return handleApiResponse {
            apiCycleService.getCycles(routineId)
        }
    }

}