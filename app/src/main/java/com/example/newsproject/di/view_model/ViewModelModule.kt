package com.example.newshomework.di.view_model

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.newsproject.ui.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindLoginViewModel(viewModel: NewsViewModel): ViewModel
}