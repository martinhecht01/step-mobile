package com.example.step_mobile.data.network

class DataSourceException(
    code: Int,
    message: String,
    details: List<String>?
) : Exception(message)