package com.userposts.testapplication.data.network.service

import com.userposts.testapplication.data.network.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersServiceApi {

    @GET("/users/{user_id}")
    suspend fun getUserInfoById(@Path("user_id") userId: Int): UserResponse
}