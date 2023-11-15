package com.kevin.andriodmviktor.newsscreen.model.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.andriodmviktor.newsscreen.intent.NewsIntent
import com.kevin.andriodmviktor.newsscreen.model.model.MyStateModel
import com.kevin.andriodmviktor.newsscreen.model.viewState.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val myModel: MyStateModel) : ViewModel() {


    private val _state = mutableStateOf<NewsState>(NewsState.Idle)
    val state: State<NewsState> = _state


    fun processIntent(intent: NewsIntent) {
        // add error handling
        viewModelScope.launch {
            Log.e("Tag", "process intent " + intent.javaClass.name)
            val curState = myModel.processIntent(intent, _state.value)
            _state.value = curState
        }
    }
}