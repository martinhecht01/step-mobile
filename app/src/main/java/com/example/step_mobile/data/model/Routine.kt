package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkRoutine
import com.google.gson.annotations.SerializedName
import java.util.Date


data class Routine(
    var id         : Int,
    var name       : String,
    var detail     : String,
    var date       : Date?,
    var score      : Int?,
    var isPublic   : Boolean?,
    var difficulty : String?,
    var user       : User?,
    var category   : Category?,
    var metadata   : String?
) {
    fun asNetworkModel(): NetworkRoutine {
        return NetworkRoutine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            isPublic = isPublic,
            difficulty = difficulty,
            user = user,
            category = category,
            metadata = metadata
        )
    }
}