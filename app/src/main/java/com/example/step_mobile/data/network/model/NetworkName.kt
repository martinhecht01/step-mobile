package com.example.step_mobile.data.network.model


import com.example.step_mobile.data.model.Name
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkName (
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
) {

    fun asModel() : Name {
        return Name(
            firstName = firstName,
            lastName = lastName,
        )
    }
}