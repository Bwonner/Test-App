package com.userposts.testapplication.data.repository

import com.userposts.testapplication.domain.model.UserPostResult
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun getPosts(): Flow<Result<List<UserPostResult>>>

    suspend fun refreshPosts()
}