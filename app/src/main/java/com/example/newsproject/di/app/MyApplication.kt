package com.example.newsproject.di.app

import android.app.Application
import com.example.core.HasDependencies

class MyApplication() : Application(), HasDependencies {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override val dependencies: Any
        get() = appComponent
}