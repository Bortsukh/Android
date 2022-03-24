package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.model.api.Api
import com.example.myapplication.model.db.AppDataBase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    lateinit var api: Api

    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database"
        ).allowMainThreadQueries().build()

        initRetrofit()
    }

    private fun initRetrofit() {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .build()
                val response = chain.proceed(
                    chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                )
                return@addInterceptor response
            }
            .addInterceptor(
                HttpLoggingInterceptor()
                .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(Api::class.java)
    }

    companion object {
        const val BASE_URL = "https://kitsu.io/api/edge/"

        lateinit var instance: App
            private set

        const val PAGE_SIZE = 10
    }
}