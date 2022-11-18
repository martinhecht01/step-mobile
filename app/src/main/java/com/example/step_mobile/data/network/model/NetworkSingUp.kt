package com.example.step_mobile.data.network.model

import com.example.step_mobile.data.model.SignUp
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkSignUp (

    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("gender")
    var gender: String? = "male",
    @SerializedName("birthdate")
    var birthdate: Int? = null,
    @SerializedName("email")
    var email: String,
    @SerializedName("phone")
    var phone: String? = "",
    @SerializedName("avatarUrl")
    var avatarUrl: String? = "",
    @SerializedName("metadata")
    var metadata: NetworkUserMetadata? = null

)