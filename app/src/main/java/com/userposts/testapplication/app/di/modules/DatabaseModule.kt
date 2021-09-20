package com.userposts.testapplication.app.di.modules

import android.content.Context
import androidx.room.Room
import com.userposts.testapplication.data.db.Database
import com.userposts.testapplication.data.db.dao.PostsDao
import com.userposts.testapplication.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    companion object {
        @Singleton
        @Provides
        fun provideDatabase(context: Context): Database =
            Room
                .databaseBuilder(context, Database::class.java, Database::class.java.simpleName)
                .fallbackToDestructiveMigration()
                .build()

        @Singleton
        @Provides
        fun providePostsDao(database: Database): PostsDao = database.getPostsDao()

        @Singleton
        @Provides
        fun provideUserDao(database: Database): UserDao = database.getUserDao()
    }
}