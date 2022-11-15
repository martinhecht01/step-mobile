package com.example.step_mobile.data.network

import com.example.step_mobile.data.network.api.ApiRoutineService
import com.example.step_mobile.data.network.model.NetworkPagedContent
import com.example.step_mobile.data.network.model.NetworkRoutine


class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {

    suspend fun getRoutines() : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines()
        }
    }

    suspend fun getRoutine(routineId: Int) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }

    suspend fun addRoutine(routine: NetworkRoutine) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.addRoutine(routine)
        }
    }

    suspend fun modifyRoutine(routine: NetworkRoutine) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.modifyRoutine(routine.id!!, routine)
        }
    }

    suspend fun deleteRoutine(routineId: Int){
        handleApiResponse {
            apiRoutineService.deleteRoutine(routineId)
        }
    }

    suspend fun getFavourites() : NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiRoutineService.getFavourites()
        }
    }

    suspend fun deleteFromFavourites(routineId : Int){
        handleApiResponse {
            apiRoutineService.deleteFromFavourites(routineId)
        }
    }

    suspend fun addToFavourites(routineId: Int){
        handleApiResponse {
            apiRoutineService.addToFavourites(routineId)
        }
    }
}