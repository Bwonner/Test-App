package com.userposts.testapplication.app.di.component

import android.app.Application
import android.content.Context
import com.userposts.testapplication.app.MainActivity
import com.userposts.testapplication.app.di.modules.AppModule
import com.userposts.testapplication.app.di.modules.DatabaseModule
import com.userposts.testapplication.app.di.modules.NetworkModule
import com.userposts.testapplication.app.di.modules.RepositoryModule
import com.userposts.testapplication.domain.interactor.PostsInteractor
import com.userposts.testapplication.feature.InternetObserver
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class]
)
interface AppComponent {

    fun inject(application: Application)
    fun inject(activity: MainActivity)

    val postsInteractor: PostsInteractor
    val internetStateObserver: InternetObserver

    @Component.Factory
    interface Builder {
        fun build(@BindsInstance context: Context): AppComponent
    }

    companion object {
        operator fun invoke(context: Context): AppComponent =
            DaggerAppComponent.factory().build(context)
    }
}