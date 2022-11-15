package com.example.step_mobile.data.model

import java.util.*

data class Exercise (

    var id       : Int,
    var name     : String,
    var detail   : String,
    var type     : String?,
    var date     : Date?,
    var metadata : String?
)