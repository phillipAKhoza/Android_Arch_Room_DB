package com.phillip_dev.knowtes.Notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {
    private  val CHANNEL_ID = "1"
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null){
            val builder = NotificationCompat.Builder(context,CHANNEL_ID)
        }
    }
}