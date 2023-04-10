package com.example.data.mappers

import com.example.data.database.NewsEntity
import com.example.domain.NewsData
import javax.inject.Inject

class DataBaseMapper @Inject constructor() {
    fun dataToEntity(data: NewsData): NewsEntity = with(data) {
        NewsEntity(
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage
        )
    }

    fun entityToData(data: List<NewsEntity>): List<NewsData> {
        return data.map {
            NewsData(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage
            )
        }
    }
}

