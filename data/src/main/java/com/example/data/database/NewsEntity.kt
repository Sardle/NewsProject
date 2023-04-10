package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo val author: String,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String,
    @PrimaryKey val url: String,
    @ColumnInfo val urlToImage: String
)