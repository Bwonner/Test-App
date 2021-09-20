package com.userposts.testapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.userposts.testapplication.data.db.entity.PostEntity
import com.userposts.testapplication.data.db.entity.UserPostResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = PostEntity::class)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query(
        """
        SELECT u.name as name,
        p.title as title
        FROM posts as p
        JOIN users as u ON p.userId = u.id
        """
    )
    fun getPosts(): Flow<List<UserPostResultEntity>>
}