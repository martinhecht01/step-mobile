package com.example.step_mobile.data.network

import com.example.step_mobile.util.SessionManager
import com.example.step_mobile.data.network.api.ApiUserService
import com.example.step_mobile.data.network.model.NetworkCredentials
import com.example.step_mobile.data.network.model.NetworkUser


class UserRemoteDataSource(
    private val apiUserService: ApiUserService,
    private val sessionManager: SessionManager
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { apiUserService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser() : NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }
}