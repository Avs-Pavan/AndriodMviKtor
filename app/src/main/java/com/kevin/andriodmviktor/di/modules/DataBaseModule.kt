package com.kevin.andriodmviktor.di.modules

import android.content.Context
import androidx.room.Room
import com.kevin.andriodmviktor.di.room.NewsDataBase
import com.kevin.andriodmviktor.newsscreen.model.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideNewsDao(newsDataBase: NewsDataBase): NewsDao {
        return newsDataBase.getNewsDao()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): NewsDataBase {
        return Room.databaseBuilder(
            appContext,
            NewsDataBase::class.java,
            "news",
        ).build()
    }
}