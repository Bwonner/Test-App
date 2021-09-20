package com.userposts.testapplication.domain.model


data class Address(
    val street: String = "",
    val suite: String = "",
    val city: String = "",
    val zipcode: String = "",
    val geo: Geo = Geo()
)
