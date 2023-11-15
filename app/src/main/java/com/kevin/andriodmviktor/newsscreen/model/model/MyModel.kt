package com.kevin.andriodmviktor.newsscreen.model.model

import com.kevin.andriodmviktor.newsscreen.intent.NewsIntent
import com.kevin.andriodmviktor.newsscreen.model.repository.NewsRepository
import com.kevin.andriodmviktor.newsscreen.model.viewState.NewsState
import javax.inject.Inject

class MyModel @Inject constructor(private val repository: NewsRepository) {

    suspend fun processIntent(intent: NewsIntent, state: NewsState): NewsState {
        return when (intent) {
            is NewsIntent.AddNewsIntent -> {
                repository.addNews(intent.newsData)
                NewsState.ActionSuccess("News added successfully")
            }

            is NewsIntent.DeleteNewsIntent -> {
                repository.deleteNews(intent.newsData)
                NewsState.ActionSuccess("News Deleted successfully")
            }

            is NewsIntent.UpdateNewsIntent -> {
                repository.updateNews(intent.newsData)
                NewsState.ActionSuccess("News updated successfully")
            }

            is NewsIntent.GetNewsIntent -> {
                NewsState.NewsList(repository.getNews())
            }
        }
    }
}