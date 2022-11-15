package com.example.step_mobile.data.network.api


import com.example.step_mobile.data.network.model.NetworkCredentials
import com.example.step_mobile.data.network.model.NetworkName
import com.example.step_mobile.data.network.model.NetworkToken
import com.example.step_mobile.data.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiUserService {
    @POST("users/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("users/logout")
    suspend fun logout(): Response<Unit>

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUser>

    @PUT("users/current")
    suspend fun modifyUser(@Body newName : NetworkName): Response<NetworkUser>
}
