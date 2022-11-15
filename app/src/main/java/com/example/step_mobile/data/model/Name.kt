package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkName

class Name(
    var firstName: String,
    var lastName: String,
) {
    fun asNetworkModel(): NetworkName {
        return NetworkName(
            firstName = firstName,
            lastName = lastName,
        )
    }
}
