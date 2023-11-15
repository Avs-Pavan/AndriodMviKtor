package com.kevin.andriodmviktor.newsscreen.intent

import com.kevin.andriodmviktor.newsscreen.model.db.entity.NewsData

sealed class NewsIntent {
    class AddNewsIntent(val newsData: NewsData) : NewsIntent()
    class DeleteNewsIntent(val newsData: NewsData) : NewsIntent()
    class UpdateNewsIntent(val newsData: NewsData) : NewsIntent()
    object GetNewsIntent : NewsIntent()
}

