package com.userposts.testapplication.data.network.dto

import com.google.gson.annotations.SerializedName

data class CompanyInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("bs")
    val bs: String
)
