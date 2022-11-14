package com.example.step_mobile.data.network.model


import com.example.step_mobile.data.model.Category
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.data.model.User
import com.google.gson.annotations.SerializedName

class NetworkRoutine (
    @SerializedName("id"         ) var id         : Int,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String,
    @SerializedName("date"       ) var date       : Int?,
    @SerializedName("score"      ) var score      : Int?,
    @SerializedName("isPublic"   ) var isPublic   : Boolean?,
    @SerializedName("difficulty" ) var difficulty : String?,
    @SerializedName("user"       ) var user       : User?,
    @SerializedName("category"   ) var category   : Category?,
    @SerializedName("metadata"   ) var metadata   : String?
) {

    fun asModel() : Routine {
        return Routine(
            id =         id,
            name =       name,
            detail =     detail,
            date  =      date,
            score      = score,
            isPublic  =  isPublic,
            difficulty = difficulty,
            user       = user,
            category =   category,
            metadata =   metadata
        )
    }
}