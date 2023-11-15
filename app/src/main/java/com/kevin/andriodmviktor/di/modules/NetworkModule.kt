package com.kevin.andriodmviktor.di.modules

import com.kevin.andriodmviktor.di.interceptors.ApiKeyInterceptor
import com.kevin.andriodmviktor.di.modules.Constants.NEWS_API_URL
import com.kevin.andriodmviktor.remotenewsscreen.model.repository.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return NEWS_API_URL
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }


    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideInterceptor() = ApiKeyInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor) =
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }


}