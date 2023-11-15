package com.kevin.andriodmviktor.remotenewsscreen.intent

sealed class RemoteNewsIntent {
    object GetNewsIntent : RemoteNewsIntent()
}