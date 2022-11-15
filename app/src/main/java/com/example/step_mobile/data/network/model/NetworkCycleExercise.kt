package com.example.step_mobile.data.network.model

import com.example.step_mobile.data.model.CycleExercise
import com.example.step_mobile.data.model.Exercise
import com.google.gson.annotations.SerializedName

class NetworkCycleExercise (
    @SerializedName("order"       ) var order       : Int      ,
    @SerializedName("duration"    ) var duration    : Int      ,
    @SerializedName("repetitions" ) var repetitions : Int    ,
    @SerializedName("exercise"    ) var exercise    : Exercise? = null

){
    fun asModel() : CycleExercise {
        return CycleExercise(
            order           =order           ,
            duration        =duration        ,
            repetitions     =repetitions     ,
            exercise        =exercise

        )
    }
}