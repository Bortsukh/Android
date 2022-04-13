package com.example.myapplication.di.modules

import android.app.Application
import androidx.room.Room
import com.example.myapplication.model.db.AppDataBase
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun db(context: Application): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }
}