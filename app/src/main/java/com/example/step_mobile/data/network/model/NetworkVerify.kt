package com.example.step_mobile.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkVerify (
        @SerializedName("email")
        var email: String,
        @SerializedName("code")
        var codeString: String
        )
