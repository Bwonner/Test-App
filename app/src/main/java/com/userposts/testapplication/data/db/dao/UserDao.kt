package com.userposts.testapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.userposts.testapplication.data.db.entity.PostEntity
import com.userposts.testapplication.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = UserEntity::class)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = UserEntity::class)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users ORDER BY id")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity>
}