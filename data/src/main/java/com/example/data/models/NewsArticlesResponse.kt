package com.example.data.models

import com.squareup.moshi.Json

data class NewsArticlesResponse(
    @Json(name = "articles") val articles: List<NewsResponse>? = null
)
