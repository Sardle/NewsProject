package com.example.data.source

import com.example.data.database.NewsDao
import com.example.data.database.NewsEntity
import io.reactivex.Observable
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val dao: NewsDao
) {

    fun getAll(): Observable<List<NewsEntity>> = dao.getAll()

    fun insertAll(users: List<NewsEntity>) = dao.insertAll(users)

    fun delete(users: List<NewsEntity>) = dao.delete(users)

    fun getAllNewsDataBase(): List<NewsEntity> = dao.getAllNewsDataBase()
}