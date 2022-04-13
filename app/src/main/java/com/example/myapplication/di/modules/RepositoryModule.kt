package com.example.myapplication.di.modules

import com.example.myapplication.model.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(): Repository {
        return Repository()
    }
}