package com.userposts.testapplication.app.di.modules

import com.userposts.testapplication.data.repository.PostsRepository
import com.userposts.testapplication.data.repository.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPostsRepository(repository: PostsRepositoryImpl): PostsRepository
}