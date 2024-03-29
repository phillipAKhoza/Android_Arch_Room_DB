package com.phillip_dev.knowtes.Notification

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.phillip_dev.knowtes.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null){
            val CHANNEL_ID = "1"
            val builder = NotificationCompat.Builder(context,CHANNEL_ID)
            val title = intent?.getStringExtra("rTitle")
            val description = intent?.getStringExtra("rDescription")
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                val rChannel = NotificationChannel(CHANNEL_ID,"note reminder",NotificationManager.IMPORTANCE_DEFAULT)
                val rManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                rManager.createNotificationChannel(rChannel)
                builder.setSmallIcon(R.drawable.notifications_icon)
                    .setContentTitle("Reminder: $title")
                    .setContentText(description)

            }else{
                builder.setContentTitle("Reminder: $title")
                    .setContentText(description)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }

            val notificationManagerCompat = NotificationManagerCompat.from(context)
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
            }
            notificationManagerCompat.notify(1,builder.build())
        }
    }
}