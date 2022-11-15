package com.example.step_mobile

import android.app.Application
import com.example.step_mobile.data.network.*
import com.example.step_mobile.data.network.api.RetrofitClient
import com.example.step_mobile.data.repository.*
import com.example.step_mobile.util.SessionManager

class StepApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    private val cycleRemoteDataSource: CycleRemoteDataSource
        get() = CycleRemoteDataSource(RetrofitClient.getApiCycleService(this))
    private val cycleExerciseRemoteDataSource: CycleExerciseRemoteDataSource
        get() = CycleExerciseRemoteDataSource(RetrofitClient.getApiCycleExerciseService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)
    val cycleRepository: CycleRepository
        get() = CycleRepository(cycleRemoteDataSource)

    val cycleExerciseRepository: CycleExerciseRepository
        get() = CycleExerciseRepository(cycleExerciseRemoteDataSource)
}