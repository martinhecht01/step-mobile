package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkReview
import com.example.step_mobile.data.network.model.NetworkRoutine
import java.util.*

data class Review(
    var score         : Int,
    var review       : String,
) {

    fun asNetworkModel(): NetworkReview {
        return NetworkReview(
            score = score,
            review = review,

        )
    }
}