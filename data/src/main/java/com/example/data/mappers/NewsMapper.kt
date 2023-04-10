package com.example.data.mappers

import com.example.data.models.NewsResponse
import com.example.domain.NewsData
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    operator fun invoke(response: NewsResponse): NewsData = with(response) {
        NewsData(
            author = author.orEmpty(),
            title = title.orEmpty(),
            description = description.orEmpty(),
            url = url.orEmpty(),
            urlToImage = urlToImage.orEmpty()
        )
}
}