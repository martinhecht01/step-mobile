package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkVerify

data class Verify(
    var email: String,
    var code: String
) {
    fun asNetworkModel(): NetworkVerify {
        return NetworkVerify(
            email = email,
            codeString = code
        )
    }
}

