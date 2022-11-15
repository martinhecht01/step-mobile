package com.example.step_mobile.data.network.model


import com.example.step_mobile.data.model.Category
import com.example.step_mobile.data.model.Review
import com.example.step_mobile.data.model.Routine
import com.example.step_mobile.data.model.User
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkReview (
    @SerializedName("score"      ) var score      : Int,
    @SerializedName("review"   )  var review   : String,

) {

    fun asModel() : Review {
        return Review(
            score      = score,
            review  =  review,
        )
    }
}