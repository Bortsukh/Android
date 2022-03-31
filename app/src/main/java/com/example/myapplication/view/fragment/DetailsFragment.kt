package com.example.myapplication.view.fragment

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.broadcastreceiver.MyNotificationPublisher
import com.example.myapplication.view.activity.MainActivity
import java.util.*

class DetailsFragment : Fragment() {

    companion object{
        const val NOTIFICATION_CHANNEL_ID = "10001"
        const val default_notification_channel_id = "default"
        const val FILM_DETAILS = "FILM_DETAILS"
        const val FILM_IMAGE = "FILM_IMAGE"
    }

    val currentDate = Calendar.getInstance()
    private var details: String? = null
    private var image: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        details = arguments?.getString(FILM_DETAILS)
        image = arguments?.getString(FILM_IMAGE)
        view.findViewById<TextView>(R.id.details_text).text = details
        val im = view.findViewById<ImageView>(R.id.image_details)
        val setDateButton = view.findViewById<Button>(R.id.set_date)
        setDateButton.setOnClickListener { setDate() }
        Glide.with(im.context)
            .load(image)
            .placeholder(R.drawable.harry_potter)
            .error(R.drawable.star_wars)
            .into(im)
        return view
    }

    private fun setDate() {
        DatePickerDialog(
            requireActivity(),{dp,y,m,d ->
                currentDate.set(Calendar. YEAR , y) ;
                currentDate.set(Calendar. MONTH , m) ;
                currentDate.set(Calendar. DAY_OF_MONTH , d) ;
                scheduleNotification(getNotification("no matters"),currentDate.time.time)
                Toast.makeText(context,"$y ${m+1} $d", Toast.LENGTH_LONG).show()
            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun getNotification(content: String): Notification {
        val builderNotification = NotificationCompat.Builder(requireContext(), default_notification_channel_id)
        builderNotification.setContentTitle( "It's time to watch" )
        builderNotification.setSmallIcon(R.drawable. ic_launcher_foreground )
        builderNotification.setAutoCancel( true )
        builderNotification.setChannelId( NOTIFICATION_CHANNEL_ID )
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(FILM_DETAILS, details)
        intent.putExtra(FILM_IMAGE, image)
        builderNotification.setContentIntent(PendingIntent.getActivity(requireContext(),3,intent,PendingIntent. FLAG_UPDATE_CURRENT))
        return builderNotification.build()
    }

    private fun scheduleNotification(notification: Notification, delay: Long) {
        val notificationIntent = Intent(context, MyNotificationPublisher::class.java)
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID , 1 )
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION , notification)
        val pendingIntent = PendingIntent. getBroadcast ( requireContext(), 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT )
        val alarmManager =  requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent)

    }
}