package com.kevin.andriodmviktor.remotenewsscreen.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kevin.andriodmviktor.remotenewsscreen.intent.RemoteNewsIntent
import com.kevin.andriodmviktor.remotenewsscreen.model.models.Article
import com.kevin.andriodmviktor.remotenewsscreen.model.statemodel.RemoteNewsState
import com.kevin.andriodmviktor.remotenewsscreen.model.viewmodel.RemoteNewsViewModel

@Composable
fun RemoteNewsScreen(viewModel: RemoteNewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val state by viewModel.state

    // Get the list of news from the state
    val newsList = when (state) {
        is RemoteNewsState.NewsList -> (state as RemoteNewsState.NewsList).list
        else -> emptyList()
    }

    // Remember the list so that it survives recomposition
    val rememberedNewsList = remember { mutableStateListOf(*newsList.toTypedArray()) }


    when (state) {
        is RemoteNewsState.Loading -> {
            Log.e("Tag", "... loading..")
            // show loading
        }

        is RemoteNewsState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (state as RemoteNewsState.Error).error,
                Toast.LENGTH_LONG
            ).show()
            Log.e("Tag", "...error" + (state as RemoteNewsState.Error).error)

        }

        RemoteNewsState.Idle -> {
            Log.e("Tag", "...idle")
        }

        is RemoteNewsState.NewsList -> {
            // Update the remembered list when the state changes
            rememberedNewsList.clear()
            rememberedNewsList.addAll(newsList)
            Log.e("Tag", "... list" + (state as RemoteNewsState.NewsList).list.joinToString())
        }
    }

    viewModel.processIntent(RemoteNewsIntent.GetNewsIntent)

    Column {
        NewsList(newsList = rememberedNewsList)
    }

}


@Composable
fun NewsList(newsList: SnapshotStateList<Article>) {
    LazyColumn {
        items(newsList) {
            NewsRow(news = it)
        }
    }
}

@Composable
fun NewsRow(news: Article) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.Cyan, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = news.title, fontWeight = FontWeight.Bold)
            Text(text = news.content, fontStyle = FontStyle.Italic)

        }
    }

}
