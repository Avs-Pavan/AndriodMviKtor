package com.kevin.andriodmviktor.di.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kevin.andriodmviktor.newsscreen.model.db.dao.NewsDao
import com.kevin.andriodmviktor.newsscreen.model.db.entity.NewsData
import javax.inject.Singleton

@Singleton
@Database(entities = [NewsData::class], version = 1, exportSchema = false)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}