package com.kevin.andriodmviktor.newsscreen.model.repository

import androidx.lifecycle.LiveData
import com.kevin.andriodmviktor.newsscreen.model.db.entity.NewsData


interface NewsRepository {
    suspend fun addNews(newsData: NewsData)
    suspend fun updateNews(newsData: NewsData)
    suspend fun deleteNews(newsData: NewsData)
    suspend fun getNews(): List<NewsData>
}