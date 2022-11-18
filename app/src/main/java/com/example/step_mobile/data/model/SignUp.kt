package com.example.step_mobile.data.model

import com.example.step_mobile.data.network.model.NetworkSignUp
import java.util.*

data class SignUp(
    var username: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String
) {
    fun asNetworkModel(): NetworkSignUp {
        return NetworkSignUp(
            username = username,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            birthdate = 0,
            gender = "male",
            phone = " ",
            avatarUrl = " ",
            metadata = null
        )
    }
}
