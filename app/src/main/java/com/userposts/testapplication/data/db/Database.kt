package com.userposts.testapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.userposts.testapplication.data.db.dao.PostsDao
import com.userposts.testapplication.data.db.dao.UserDao
import com.userposts.testapplication.data.db.entity.PostEntity
import com.userposts.testapplication.data.db.entity.UserEntity

@Database(entities = [UserEntity::class, PostEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getPostsDao(): PostsDao
}