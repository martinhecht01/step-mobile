package com.example.step_mobile.data.network.model

import com.example.step_mobile.data.model.Content
import com.example.step_mobile.data.model.Cycle
import com.example.step_mobile.data.model.Routine
import com.google.gson.annotations.SerializedName

data class NetworkCycle (
    @SerializedName("totalCount" ) var totalCount : Int?               = null,
    @SerializedName("orderBy"    ) var orderBy    : String?            = null,
    @SerializedName("direction"  ) var direction  : String?            = null,
    @SerializedName("content"    ) var content    : List<Content> = listOf(),
    @SerializedName("size"       ) var size       : Int?               = null,
    @SerializedName("page"       ) var page       : Int?               = null,
    @SerializedName("isLastPage" ) var isLastPage : Boolean?           = null
){
    fun asModel() : Cycle {
        return Cycle(
            totalCount =totalCount,
            orderBy    =orderBy   ,
            direction  =direction ,
            content    =content   ,
            size       =size      ,
            page       =page      ,
            isLastPage =isLastPage
        )
    }
}