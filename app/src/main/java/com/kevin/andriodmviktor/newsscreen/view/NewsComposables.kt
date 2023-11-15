package com.kevin.andriodmviktor.newsscreen.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kevin.andriodmviktor.newsscreen.intent.NewsIntent
import com.kevin.andriodmviktor.newsscreen.model.db.entity.NewsData
import com.kevin.andriodmviktor.newsscreen.model.viewModel.NewsViewModel
import com.kevin.andriodmviktor.newsscreen.model.viewState.NewsState

@Composable
fun NewsScreen(viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val state by viewModel.state

    // Get the list of news from the state
    val newsList = when (state) {
        is NewsState.NewsList -> (state as NewsState.NewsList).list
        else -> emptyList()
    }

    // Remember the list so that it survives recomposition
    val rememberedNewsList = remember { mutableStateListOf(*newsList.toTypedArray()) }




    when (state) {
        is NewsState.Loading -> {
            Log.e("Tag", "... loading..")
        }

        is NewsState.ActionSuccess -> {
            Toast.makeText(
                LocalContext.current,
                (state as NewsState.ActionSuccess).success,
                Toast.LENGTH_LONG
            ).show()
            Log.e("Tag", "...success " + (state as NewsState.ActionSuccess).success)

        }

        is NewsState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (state as NewsState.Error).error,
                Toast.LENGTH_LONG
            ).show()
            Log.e("Tag", "...error" + (state as NewsState.Error).error)

        }

        NewsState.Idle -> {
            Log.e("Tag", "...idle")
        }

        is NewsState.NewsList -> {
            // Update the remembered list when the state changes
            rememberedNewsList.clear()
            rememberedNewsList.addAll(newsList)
            Log.e("Tag", "... list" + (state as NewsState.NewsList).list.joinToString())
        }
    }

    viewModel.processIntent(NewsIntent.GetNewsIntent)

    Column {
        UserInput { title, content ->
            viewModel.processIntent(NewsIntent.AddNewsIntent(NewsData(0, title, content)))
        }
        Spacer(modifier = Modifier.height(10.dp))
        NewsList(newsList = rememberedNewsList)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(addNews: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text(text = "Content") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            addNews(title, content)
            title = ""
            content = ""
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save")
        }
    }
}

@Composable
fun NewsList(newsList: SnapshotStateList<NewsData>) {
    LazyColumn {
        items(newsList) {
            NewsRow(news = it)
        }
    }
}

@Composable
fun NewsRow(news: NewsData) {
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
