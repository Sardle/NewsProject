package com.example.newsproject.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.AppDataBase
import com.example.data.database.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "database-name")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDataBase): NewsDao = db.getNewsDao()
}