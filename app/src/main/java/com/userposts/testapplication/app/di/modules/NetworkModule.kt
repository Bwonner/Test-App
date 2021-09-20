package com.userposts.testapplication.app.di.modules

import android.content.Context
import com.userposts.testapplication.BuildConfig
import com.userposts.testapplication.data.network.service.PostsServiceApi
import com.userposts.testapplication.data.network.service.UsersServiceApi
import com.userposts.testapplication.feature.InternetObserver
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    companion object {
        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Singleton
        @Provides
        fun providePostsApi(retrofit: Retrofit): PostsServiceApi =
            retrofit.create(PostsServiceApi::class.java)

        @Singleton
        @Provides
        fun provideUserApi(retrofit: Retrofit): UsersServiceApi =
            retrofit.create(UsersServiceApi::class.java)

        @Singleton
        @Provides
        fun provideInternetObserver(context: Context) =
            InternetObserver(context)
    }
}