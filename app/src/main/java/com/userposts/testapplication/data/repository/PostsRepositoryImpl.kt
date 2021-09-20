package com.userposts.testapplication.data.repository

import com.userposts.testapplication.data.db.dao.PostsDao
import com.userposts.testapplication.data.db.dao.UserDao
import com.userposts.testapplication.data.network.service.PostsServiceApi
import com.userposts.testapplication.data.network.service.UsersServiceApi
import com.userposts.testapplication.data.toPostEntity
import com.userposts.testapplication.data.toUserEntity
import com.userposts.testapplication.data.toUserPostResult
import com.userposts.testapplication.domain.model.UserPostResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsDao: PostsDao,
    private val userDao: UserDao,
    private val postsServiceApi: PostsServiceApi,
    private val usersServiceApi: UsersServiceApi
) : PostsRepository {

    override suspend fun getPosts(): Flow<Result<List<UserPostResult>>> =
        postsDao
            .getPosts()
            .map { it.map { userPostResult -> userPostResult.toUserPostResult() } }
            .flatMapConcat { posts ->
                flow {
                    if (posts.isEmpty()) {
                        try {
                            refreshPosts()
                        } catch (ex: Exception) {
                            emit(Result.failure<List<UserPostResult>>(ex))
                        }
                    } else {
                        emit(Result.success(posts))
                    }
                }
            }

    override suspend fun refreshPosts() {
        val posts = postsServiceApi.getPosts()
        val userIds = posts.map { postResponse -> postResponse.userId }.toSortedSet()

        userIds
            .map { userId -> usersServiceApi.getUserInfoById(userId) }
            .asFlow()
            .catch { ex -> throw ex }
            .map { userResponse -> userResponse.toUserEntity() }
            .toList()
            .also { users -> userDao.insertUsers(users) }

        posts
            .map { postResponse -> postResponse.toPostEntity() }
            .also { postsDao.insertPosts(it) }
    }
}