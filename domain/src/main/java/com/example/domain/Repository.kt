package com.example.domain

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


interface Repository {

    fun getNews(): Completable

    fun getNewsFromDataBase(): Observable<List<NewsData>>

    fun setUserToken(token: String)

    fun search(search: String): Single<List<NewsData>>
}