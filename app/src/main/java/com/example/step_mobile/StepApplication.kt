package com.example.step_mobile

import android.app.Application
import com.example.step_mobile.data.network.RoutineRemoteDataSource
import com.example.step_mobile.data.network.SportRemoteDataSource
import com.example.step_mobile.data.network.UserRemoteDataSource
import com.example.step_mobile.data.network.api.RetrofitClient
import com.example.step_mobile.data.repository.RoutineRepository
import com.example.step_mobile.data.repository.SportRepository
import com.example.step_mobile.data.repository.UserRepository
import com.example.step_mobile.util.SessionManager

class StepApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)
}