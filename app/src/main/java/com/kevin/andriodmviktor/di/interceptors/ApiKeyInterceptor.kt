package com.kevin.andriodmviktor.di.interceptors

import com.kevin.andriodmviktor.di.modules.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val actualRequest = chain.request()

        val newUrl =
            actualRequest.url.newBuilder()
                .addQueryParameter("apiKey", Constants.NEWS_API_KEY)
                .build()

        val modifiedRequest = actualRequest.newBuilder().url(newUrl).build()
        return chain.proceed(modifiedRequest)
    }
}