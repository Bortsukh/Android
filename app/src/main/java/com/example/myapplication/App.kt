package com.example.myapplication

import android.app.Application
import com.example.myapplication.model.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var api: Api

    override fun onCreate() {
        super.onCreate()
        instance = this

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
//                    if (BuildConfig.) {
                        level = HttpLoggingInterceptor.Level.BODY
//                    }
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
    }
}