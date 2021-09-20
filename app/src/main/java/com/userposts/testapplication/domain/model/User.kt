package com.userposts.testapplication.domain.model

data class User(
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val address: Address = Address(),
    val phone: String = "",
    val website: String = "",
    val companyInfo: Company = Company()
)
