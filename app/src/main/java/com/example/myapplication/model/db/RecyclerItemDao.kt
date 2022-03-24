package com.example.myapplication.model.db

import androidx.paging.DataSource
import androidx.room.*


@Dao
interface RecyclerItemDao {
    @Query("SELECT * FROM recycleritem")
    fun getAll(): DataSource.Factory<Int, RecyclerItem>?

    @Query("UPDATE recycleritem SET isFavorite =:favorite WHERE nameFilm =:name")
    fun updateFavourites(name: String, favorite: Int )

    @Query("SELECT * FROM recycleritem WHERE isFavorite = 1")
    fun getAllFavourites(): DataSource.Factory<Int, RecyclerItem>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAll(recyclerItem: List<RecyclerItem>?)
}