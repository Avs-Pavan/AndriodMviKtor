package com.kevin.andriodmviktor.remotenewsscreen.model.statemodel

import android.util.Log
import com.kevin.andriodmviktor.remotenewsscreen.intent.RemoteNewsIntent
import com.kevin.andriodmviktor.remotenewsscreen.model.repository.RemoteNewRepository
import javax.inject.Inject

class NewsState @Inject constructor(private val repository: RemoteNewRepository) {

    suspend fun processIntent(intent: RemoteNewsIntent, state: RemoteNewsState): RemoteNewsState? {

        return when (intent) {
            RemoteNewsIntent.GetNewsIntent -> {
                try {
                    Log.e(TAG, "Fetching news from api")
                    val response = repository.getNews()
                    if (response.isSuccessful) {
                        Log.e(TAG, "Fetching news from api -- success")
                        response.body()?.articles?.let { RemoteNewsState.NewsList(it) }
                    } else {
                        Log.e(TAG, "Fetching news from api -- failed")
                        RemoteNewsState.Error("Error fetching news")
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e(TAG, "Fetching news from api -- Exception")
                    RemoteNewsState.Error("Error ${e.message}")
                }
            }
        }
    }

    companion object {
        private val TAG = "NewsState"
    }

}