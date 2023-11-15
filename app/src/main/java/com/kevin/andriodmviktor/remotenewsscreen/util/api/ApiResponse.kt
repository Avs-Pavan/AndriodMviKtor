package com.kevin.andriodmviktor.remotenewsscreen.util.api

import com.google.android.gms.common.api.Api
import com.kevin.andriodmviktor.remotenewsscreen.model.models.Article

sealed class ApiResponse<out T> {
    data class OnSuccess<out T>(val data: T) : ApiResponse<T>()
    data class OnError<out T>(val error: String) : ApiResponse<T>()
    object Loading : ApiResponse<Nothing>()
}