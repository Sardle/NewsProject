package com.example.data.network

import com.example.data.models.NewsArticlesResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface NewsService {

    @GET("everything")
    fun getNews(
        @Query("q") q: String,
        @Header("x-api-key") value: String
    ): Single<NewsArticlesResponse>
}