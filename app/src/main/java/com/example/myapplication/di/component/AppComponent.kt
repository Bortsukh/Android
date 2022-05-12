package com.example.myapplication.di.component

import com.example.myapplication.App
import com.example.myapplication.di.modules.AppModule
import com.example.myapplication.di.modules.NetworkModule
import com.example.myapplication.di.modules.RepositoryModule
import com.example.myapplication.di.modules.StorageModule
import com.example.myapplication.model.Repository
import com.example.myapplication.model.api.Api
import com.example.myapplication.model.db.AppDataBase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, NetworkModule::class, AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun db(): AppDataBase
    fun initRetrofit(): Api
    fun injectRepository(repository: Repository)
}