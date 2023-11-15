package com.kevin.andriodmviktor.newsscreen.model.viewState

import com.kevin.andriodmviktor.newsscreen.model.db.entity.NewsData

sealed class NewsState {
    object Idle : NewsState()
    object Loading : NewsState()
    data class NewsList(val list: List<NewsData>) : NewsState()
    data class ActionSuccess(val success: String) : NewsState()
    data class Error(val error: String) : NewsState()
}