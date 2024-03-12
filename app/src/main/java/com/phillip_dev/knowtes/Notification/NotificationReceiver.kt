package com.phillip_dev.knowtes.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.phillip_dev.knowtes.R

class NotificationReceiver : BroadcastReceiver() {
    private  val CHANNEL_ID = "1"
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null){
            val builder = NotificationCompat.Builder(context,CHANNEL_ID)
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                val channel = NotificationChannel(CHANNEL_ID,"1",NotificationManager.IMPORTANCE_DEFAULT)
                val manager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)

                builder.setSmallIcon(R.drawable.notifications_icon)
                    .setContentTitle("Notification")
                    .setContentText("This is a reminder")

            }else{
                builder.setContentTitle("Reminder")
                    .setContentText("This is a reminder")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }


        }
    }
}