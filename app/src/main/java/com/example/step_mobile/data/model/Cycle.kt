package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkCycle

data class Cycle(
    var totalCount : Int?             ,
    var orderBy    : String?          ,
    var direction  : String?          ,
    var content    : List<Content>    ,
    var size       : Int?             ,
    var page       : Int?             ,
    var isLastPage : Boolean?
){
    fun asNetworkModel() : NetworkCycle {
        return NetworkCycle(
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
