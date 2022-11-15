package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkCycle
import com.example.step_mobile.data.network.model.NetworkCycleExercise
import com.google.gson.annotations.SerializedName

data class CycleExercise(
    var order       : Int     ,
    var duration    : Int     ,
    var repetitions : Int     ,
    var exercise    : Exercise?
){
    fun asNetworkModel() : NetworkCycleExercise {
        return NetworkCycleExercise(
            order           =order           ,
            duration        =duration        ,
            repetitions     =repetitions     ,
            exercise        =exercise

        )
    }
}
