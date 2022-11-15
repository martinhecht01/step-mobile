package com.example.step_mobile.data.network.api

import com.example.step_mobile.data.network.model.NetworkCycle
import com.example.step_mobile.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCycleService {
    @GET("routines/{routineId}/cycles")
    suspend fun getCycles(@Path("routineId") routineId: Int) : Response<NetworkPagedContent<NetworkCycle>>

}
