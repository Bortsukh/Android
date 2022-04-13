package com.example.myapplication.di.modules

import android.app.Application
import com.example.myapplication.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun provideApp(): Application = app
}