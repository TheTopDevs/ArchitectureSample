package com.sample.app.data.remote.models

import com.google.gson.annotations.SerializedName

data class NetworkError(
    @SerializedName("message")
    val message: String?
)
