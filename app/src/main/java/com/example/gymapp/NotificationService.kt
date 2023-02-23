package com.example.gymapp

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationService(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(userName:String){
        val activityIntent = Intent(context,MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(context,1,activityIntent,
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_add_24)
            .setContentTitle("Payment done")
            .setContentText("payment done by $userName")
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1,notification)
    }



    companion object{
        const val CHANNEL_ID = "GYM_CHANNEL"
    }
}