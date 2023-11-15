package com.kevin.andriodmviktor.remotenewsscreen.model.repository

import com.kevin.andriodmviktor.remotenewsscreen.model.repository.api.NewsApiService
import com.kevin.andriodmviktor.remotenewsscreen.model.models.RemoteNewsResponse
import retrofit2.Response
import javax.inject.Inject


class RemoteNewsRepositoryImpl @Inject constructor(private val newsApiService: NewsApiService) :
    RemoteNewRepository {
    override suspend fun getNews(): Response<RemoteNewsResponse> = newsApiService.getNews()
}