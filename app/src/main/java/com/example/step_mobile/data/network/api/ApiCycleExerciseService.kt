package com.example.step_mobile.data.network.api

import com.example.step_mobile.data.network.model.NetworkCycle
import com.example.step_mobile.data.network.model.NetworkCycleExercise
import com.example.step_mobile.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCycleExerciseService {
    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId: Int) : Response<NetworkPagedContent<NetworkCycleExercise>>
}