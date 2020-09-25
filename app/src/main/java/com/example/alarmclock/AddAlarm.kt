package com.example.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.alarmclock.BroadcastReciever.AlarmReciever
import com.example.alarmclock.Database.Alarm
import com.example.alarmclock.Utils.HelperClass
import com.example.alarmclock.ViewModel.AlarmViewModel
import kotlinx.android.synthetic.main.activity_add_alarm.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class AddAlarm : AppCompatActivity() {

    lateinit var alarmViewModel: AlarmViewModel
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_alarm)

        init()

        if (intent.hasExtra("Hours")){

            hoursPicker.value = intent.getIntExtra("Hours" , 12)
            minutesPicker.value = intent.getIntExtra("Minutes" , 12)
            amPmPicker.value = if (intent.getStringExtra("AMPM")?.equals("AM")!!) 0 else 1

        }

        val myIntent : Intent = Intent(this , AlarmReciever::class.java)
        saveAlarm.setOnClickListener {

            val value  = if (amPmPicker.value == 0) "AM" else "PM"
            val alarm :Alarm = Alarm(hoursPicker.value , minutesPicker.value , value , "yes" , "Alarm" )

            if (intent.hasExtra("Hours")){
                Log.d("Hi" , "Hi " + alarm)
                alarm.id = intent.getIntExtra("id" , 0)
                alarmViewModel.updateAlarm(alarm)
            }
            else{

                alarmViewModel.setAlarm(alarm)

                val calendar : Calendar = Calendar.getInstance()


                calendar.set(Calendar.HOUR_OF_DAY , if (amPmPicker.value == 1) hoursPicker.value + 12 else hoursPicker.value)
                calendar.set(Calendar.MINUTE , minutesPicker.value)
                calendar.set(Calendar.SECOND , 0)
                calendar.set(Calendar.MILLISECOND, 0)

                Log.d("helloo" , calendar.timeInMillis.toString())

                Log.d("hello2"  , System.currentTimeMillis().toString() )

                myIntent.putExtra("extra" , "on")
                pendingIntent = PendingIntent.getBroadcast(this , 0 , myIntent , PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP , calendar.timeInMillis + 100, pendingIntent)
            }


            finish()
        }

        cancelAlarm.setOnClickListener {
            finish()
        }




    }

    fun init(){

        // For AM PM Spinner
        val num = arrayOf<String>("AM", "PM")
        amPmPicker.minValue = 0
        amPmPicker.maxValue = num.size - 1
        amPmPicker.displayedValues = num

        // View Model
        alarmViewModel = ViewModelProvider(this , HelperClass(this).factory).get(AlarmViewModel::class.java)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    }
}