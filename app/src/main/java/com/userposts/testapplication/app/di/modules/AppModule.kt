package com.userposts.testapplication.app.di.modules

import com.userposts.testapplication.data.repository.PostsRepository
import com.userposts.testapplication.domain.interactor.PostsInteractor
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    companion object {
        @Provides
        fun providePostsInteractor(postsRepository: PostsRepository): PostsInteractor =
            PostsInteractor(postsRepository)
    }
}