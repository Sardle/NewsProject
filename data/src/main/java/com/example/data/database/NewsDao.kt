package com.example.data.database

import androidx.room.*
import io.reactivex.Observable

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): Observable<List<NewsEntity>>

    @Query("SELECT * FROM news")
    fun getAllNewsDataBase(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<NewsEntity>)

    @Delete
    fun delete(users: List<NewsEntity>)
}