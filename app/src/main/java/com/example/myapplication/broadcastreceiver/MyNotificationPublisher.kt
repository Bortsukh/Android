package com.example.myapplication.broadcastreceiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context ;
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.myapplication.view.fragment.DetailsFragment


class MyNotificationPublisher : BroadcastReceiver() {
    companion object{
        val NOTIFICATION_ID = "notification-id"
        val NOTIFICATION = "notification"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("QQQQQQQQQ", "RRRRRRRR")
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification? = intent.getParcelableExtra<Notification>(NOTIFICATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                DetailsFragment.NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            assert(notificationManager != null)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val id = intent.getIntExtra(NOTIFICATION_ID, 0)
        assert(notificationManager != null)
        notificationManager.notify(id, notification)
    }
}