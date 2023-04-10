package com.example.newsproject.di.app

import android.content.Context
import com.example.feature.di.FeatureDeps
import com.example.newsproject.di.DataBaseModule
import com.example.newsproject.di.DataModule
import com.example.newsproject.di.NetworkModule
import com.example.newsproject.di.SourceModule
import com.example.newshomework.di.view_model.ViewModelModule
import com.example.newsproject.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, DataBaseModule::class, DataModule::class, NetworkModule::class, SourceModule::class])
interface ApplicationComponent : FeatureDeps {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
}