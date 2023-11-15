package com.kevin.andriodmviktor.remotenewsscreen.model.repository

import com.kevin.andriodmviktor.remotenewsscreen.model.models.Article
import com.kevin.andriodmviktor.remotenewsscreen.model.models.RemoteNewsResponse
import retrofit2.Response

interface RemoteNewRepository {
    suspend fun getNews(): Response<RemoteNewsResponse>
}