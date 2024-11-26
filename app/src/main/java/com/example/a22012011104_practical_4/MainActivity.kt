package com.example.a22012011104_practical_4

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showTimePickerDialog(){

        val calender = Calendar.getInstance();
        val hour = calender.get(Calendar.HOUR_OF_DAY);
        val min = calender.get(Calendar.MINUTE)
        Log.d("Before ", "Before Time Picked")
        val timepickerdialog = TimePickerDialog(this,{_,selectedhour,selectedmin->setAlarm(selectedhour,selectedmin)}
            , hour,min,true);

        timepickerdialog.show();
        Log.d("Before ", "After Time Picked")
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun setAlarm(hour:Int, min:Int){
        val calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,min);
        calendar.set(Calendar.SECOND,0);
        Log.d("SetAlarm ", "Alarm Intent")

        val alarmIntent = Intent(this,Recevier::class.java);
        Log.d("SetAlarm ", "Alarm pending Intent")

        val pendingintent = PendingIntent.getBroadcast(this,0,alarmIntent, PendingIntent.FLAG_IMMUTABLE);
/*
        Log.d("SetAlarm ", "SetExact")
        Alarmanger.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingintent)

        Log.d("SetAlarm ", " After SetExact")
        Toast.makeText(this, "Alarm set for: $hour:$min", Toast.LENGTH_SHORT).show()
*/
    }

}