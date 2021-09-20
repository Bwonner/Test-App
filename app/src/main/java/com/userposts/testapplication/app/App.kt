package com.userposts.testapplication.app

import android.app.Application
import com.userposts.testapplication.app.di.component.AppComponent

class App : Application() {

    val appComponent: AppComponent by lazy { AppComponent(this) }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}