package com.example.android_task.di

import android.content.Context
import android.content.SharedPreferences
import com.example.android_task.localdata.FilmDao
import com.example.android_task.localdata.HistoryManager
import com.example.android_task.localdata.MainDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideHistManager(@ApplicationContext context: Context): HistoryManager {
        return HistoryManager(provideSharedPreferences(context), context)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("history", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext applicationContext: Context): FilmDao {
        return MainDB.getDatabase(applicationContext).getFilmDao()
    }

}