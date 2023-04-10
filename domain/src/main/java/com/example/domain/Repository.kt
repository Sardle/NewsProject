package com.example.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getNews()

    suspend fun getNewsFromDataBase(): Flow<List<NewsData>>

    fun setUserToken(token: String)

    suspend fun search(search: String): List<NewsData>
}