package com.example.data

import com.example.data.mappers.DataBaseMapper
import com.example.data.mappers.NewsMapper
import com.example.data.network.NewsService
import com.example.data.source.DataBaseSource
import com.example.data.source.UserDataSource
import com.example.domain.NewsData
import com.example.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val mapper: NewsMapper,
    private val dbMapper: DataBaseMapper,
    private val prefs: UserDataSource,
    private val db: DataBaseSource
) : Repository {

    override suspend fun getNews() {
        return withContext(Dispatchers.IO) {
            val news = service.getNews("pc technologies", prefs.getUserToken())
                .execute()
                .body()?.articles?.map { mapper(it) } ?: throw Exception()
            val list = db.getAll()
            list.map { dbMapper.entityToData(it) }
            db.delete(db.getAllNewsDataBase())
            db.insertAll(news.map { dbMapper.dataToEntity(it) })
        }
    }

    override suspend fun getNewsFromDataBase(): Flow<List<NewsData>> = withContext(Dispatchers.IO) {
        db.getAll().map { ti -> dbMapper.entityToData(ti) }
    }

    override fun setUserToken(token: String) {
        prefs.setUserToken(token)
    }

    override suspend fun search(search: String): List<NewsData> {
        return withContext(Dispatchers.IO) {
            service.getNews(search, prefs.getUserToken())
                .execute()
                .body()?.articles?.map { mapper(it) } ?: throw Exception()
        }
    }
}
