package com.android.copycreativeroutines.adapter

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.android.copycreativeroutines.R

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        var message = intent.getStringExtra("title")
        val id= "AlarmChannel"
        val name = "AlarmCheckChannel"
        val notificationChannel =
            NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableVibration(false)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

        val builder = NotificationCompat.Builder(context, id)
            .setSmallIcon(R.drawable.ic_splash_logo)//앱 대표 이미지로
            .setContentTitle("일정 완료 알림")
            .setContentText("일정이 완료되었습니다")
            .setAutoCancel(true)



        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(notificationChannel)
        val notification = builder.build()
        manager.notify(10, notification)
    }
}