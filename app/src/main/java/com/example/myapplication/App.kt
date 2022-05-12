package com.example.myapplication

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.myapplication.di.component.AppComponent
import com.example.myapplication.di.component.DaggerAppComponent
import com.example.myapplication.di.modules.AppModule
import com.example.myapplication.di.modules.NetworkModule
import com.example.myapplication.di.modules.RepositoryModule
import com.example.myapplication.di.modules.StorageModule
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        getFcmToken()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .storageModule(StorageModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

    private fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        const val BASE_URL = "https://kitsu.io/api/edge/"

        lateinit var instance: App
            private set

        const val PAGE_SIZE = 20

        lateinit var appComponent: AppComponent
    }
}