package com.kevin.andriodmviktor.newsscreen.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String
)