package com.kevin.andriodmviktor.newsscreen.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kevin.andriodmviktor.newsscreen.model.db.entity.NewsData


@Dao
interface NewsDao {

    @Query("SELECT * FROM NEWS")
    suspend fun getAll(): List<NewsData>

    @Insert
    suspend fun insert(newsData: NewsData)

    @Update
    suspend fun update(newsData: NewsData)

    @Delete
    suspend fun delete(newsData: NewsData)

}