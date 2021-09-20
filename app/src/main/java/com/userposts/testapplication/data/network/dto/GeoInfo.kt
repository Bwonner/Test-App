package com.userposts.testapplication.data.network.dto

import com.google.gson.annotations.SerializedName

data class GeoInfo(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)
