package com.example.feature.di

import com.example.feature.ui.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SearchViewModelModule::class], dependencies = [FeatureDeps::class])
interface FeatureComponent {

    @Component.Builder
    interface Builder {
        fun deps(deps: FeatureDeps): Builder

        fun build(): FeatureComponent
    }

    fun inject(fragment: SearchFragment)
}