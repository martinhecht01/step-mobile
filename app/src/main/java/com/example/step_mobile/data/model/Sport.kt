package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkSport


class Sport(
    var id: Int? = null,
    var name: String,
    var detail: String? = null
) {
    fun asNetworkModel(): NetworkSport {
        return NetworkSport(
            id = id,
            name = name,
            detail = detail
        )
    }
}
