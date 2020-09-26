package com.example.alarmclock

import android.app.AlarmManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.Adapter.AlarmAdapter
import com.example.alarmclock.Database.Alarm
import com.example.alarmclock.Database.AlarmDatabase
import com.example.alarmclock.Repository.AlarmRepository
import com.example.alarmclock.Utils.HelperClass
import com.example.alarmclock.ViewModel.AlarmViewModel
import com.example.alarmclock.ViewModel.AlarmViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var alarmViewModel: AlarmViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        alarmViewModel = ViewModelProvider(this , HelperClass(this).factory).get(AlarmViewModel::class.java)

        print("Hi Husnain")
        print("Experiment")
        print("Hi Husnain")
        print("Hi Husnain")
        print("Hi Husnain")

        alarmViewModel.alarms.observe(this , Observer {

            alarmRecylerView.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
            alarmRecylerView.adapter = AlarmAdapter(it, this , alarmViewModel)
        })


        clearAlarm.setOnClickListener {
            alarmViewModel.deleteAlarm()
        }

        addAlarm.setOnClickListener {

            startActivity(Intent(this , AddAlarm::class.java))
        }


    }
}