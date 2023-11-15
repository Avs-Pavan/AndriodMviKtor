package com.kevin.andriodmviktor.remotenewsscreen.model.statemodel

import com.kevin.andriodmviktor.remotenewsscreen.model.models.Article

sealed class RemoteNewsState {
    object Idle : RemoteNewsState()
    object Loading : RemoteNewsState()
    data class NewsList(val list: List<Article>) : RemoteNewsState()
    data class Error(val error: String) : RemoteNewsState()

}