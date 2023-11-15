package com.kevin.andriodmviktor.remotenewsscreen.model.repository.api

import com.kevin.andriodmviktor.di.modules.Constants
import com.kevin.andriodmviktor.remotenewsscreen.model.models.RemoteNewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApiService {

    @GET(Constants.ENDPOINT)
    suspend fun getNews(): Response<RemoteNewsResponse>

}