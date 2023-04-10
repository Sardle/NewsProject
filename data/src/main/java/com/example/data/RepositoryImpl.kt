package com.example.data

import com.example.data.mappers.DataBaseMapper
import com.example.data.mappers.NewsMapper
import com.example.data.network.NewsService
import com.example.data.source.DataBaseSource
import com.example.data.source.UserDataSource
import com.example.domain.NewsData
import com.example.domain.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsMapper,
    private val dbMapper: DataBaseMapper,
    private val prefs: UserDataSource,
    private val db: DataBaseSource
) : Repository {

    override fun getNews(): Completable =
        service.getNews("pc technologies", prefs.getUserToken())
            .flatMapCompletable { single ->
                val news = single.articles?.map { mapper(it) }
                db.delete(db.getAllNewsDataBase())
                db.insertAll(news!!.map { dbMapper.dataToEntity(it) })
                Completable.complete()
            }

    override fun getNewsFromDataBase(): Observable<List<NewsData>> =
        db.getAll().map { dbMapper.entityToData(it) }


    override fun setUserToken(token: String) {
        prefs.setUserToken(token)
    }

    override fun search(search: String): Single<List<NewsData>> {
        return service.getNews(search, prefs.getUserToken()).map { single ->
            single.articles?.map { it -> mapper(it) }
        }
    }
}
