package com.example.step_mobile.data.network.api


import com.example.step_mobile.data.network.model.NetworkPagedContent
import com.example.step_mobile.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutineService {

    @GET("routines")
    suspend fun getRoutines() : Response<NetworkPagedContent<NetworkRoutine>>

    @POST("routines")
    suspend fun addRoutine(@Body routine: NetworkRoutine) : Response<NetworkRoutine>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>

    @PUT("routines/{routineId}")
    suspend fun modifyRoutine(@Path("routineId") routineId: Int, @Body routine: NetworkRoutine) : Response<NetworkRoutine>

    @DELETE("routines/{routineId}")
    suspend fun deleteRoutine(@Path("routineId") routineId: Int) : Response<Unit>

    @GET("favourites")
    suspend fun getFavourites(): Response<NetworkPagedContent<NetworkRoutine>>

    @POST("favourites/{routineId}")
    suspend fun addToFavourites(@Path("routineId") routineId: Int) : Response<Unit>

    @DELETE("favourites/{routineId}")
    suspend fun deleteFromFavourites(@Path("routineId") routineId: Int) : Response<Unit>
}