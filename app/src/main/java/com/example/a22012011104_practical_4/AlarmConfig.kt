package com.example.a22012011104_practical_4

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZoneId

class AlarmConfig(private val context: Context):AlarmInterface {
    val alarmManager = context.getSystemService(AlarmManager::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setAlarm(item: AlarmItem) {
        val intent = Intent(context,Recevier::class.java)
        intent.putExtra("Extra Message",item.message)

        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(context,item.hashCode(),intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            )


    }

    override fun cancel(item: AlarmItem) {
        val intent = Intent(context,Recevier::class.java)
    alarmManager.cancel( PendingIntent.getBroadcast(context,item.hashCode(),intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    )

    }
}