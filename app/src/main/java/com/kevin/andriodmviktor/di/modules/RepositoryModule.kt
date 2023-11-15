package com.kevin.andriodmviktor.di.modules


import com.kevin.andriodmviktor.newsscreen.model.repository.NewsRepository
import com.kevin.andriodmviktor.newsscreen.model.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}