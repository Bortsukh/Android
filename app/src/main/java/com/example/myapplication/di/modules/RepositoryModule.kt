package com.example.myapplication.di.modules

import com.example.myapplication.model.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return Repository()
    }
}