package com.userposts.testapplication.data.network.service

import com.userposts.testapplication.data.network.dto.PostResponse
import retrofit2.http.GET

interface PostsServiceApi {

    @GET("/posts")
    suspend fun getPosts(): List<PostResponse>
}