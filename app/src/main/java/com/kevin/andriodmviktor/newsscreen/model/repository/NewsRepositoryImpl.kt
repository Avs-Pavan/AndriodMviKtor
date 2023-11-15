package com.kevin.andriodmviktor.newsscreen.model.repository

import androidx.lifecycle.LiveData
import com.kevin.andriodmviktor.newsscreen.model.db.dao.NewsDao
import com.kevin.andriodmviktor.newsscreen.model.db.entity.NewsData
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val dao: NewsDao) : NewsRepository {
    override suspend fun addNews(newsData: NewsData) {
        dao.insert(newsData)
    }

    override suspend fun updateNews(newsData: NewsData) {
        dao.update(newsData)
    }

    override suspend fun deleteNews(newsData: NewsData) {
        dao.delete(newsData)
    }

    override suspend fun getNews(): List<NewsData> {
       return dao.getAll()
    }
}