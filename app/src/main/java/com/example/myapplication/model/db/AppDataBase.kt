package com.example.myapplication.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(RecyclerItem::class)], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract val recyclerItemDao: RecyclerItemDao
}