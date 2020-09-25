package com.example.alarmclock.Utils

import android.content.Context
import com.example.alarmclock.Database.AlarmDatabase
import com.example.alarmclock.Repository.AlarmRepository
import com.example.alarmclock.ViewModel.AlarmViewModelFactory

class HelperClass(context: Context) {

    val alarmDAO = AlarmDatabase.invoke(context).getAlarmDAO()
    val alarmRepository = AlarmRepository(alarmDAO)
    val factory = AlarmViewModelFactory(alarmRepository)
}