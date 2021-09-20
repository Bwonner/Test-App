package com.userposts.testapplication.domain.interactor

import com.userposts.testapplication.data.repository.PostsRepository
import com.userposts.testapplication.domain.model.UserPostResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsInteractor @Inject constructor(
    private val postsRepository: PostsRepository
) {

    suspend fun getPosts(): Flow<Result<List<UserPostResult>>> =
        withContext(Dispatchers.IO) { postsRepository.getPosts() }

    suspend fun refreshPosts() {
        withContext(Dispatchers.IO) { postsRepository.refreshPosts() }
    }
}