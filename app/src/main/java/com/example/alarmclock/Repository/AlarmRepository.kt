package com.example.alarmclock.Repository

import com.example.alarmclock.Database.Alarm
import com.example.alarmclock.Database.AlarmDAO

class AlarmRepository( var dao : AlarmDAO) {

    var alarms = dao.getAlarm()

    suspend fun setAlarm(alarm : Alarm){
        dao.setAlarm( alarm )
    }

    suspend fun updateAlarm(alarm : Alarm){
        dao.updateAlarm(alarm)
    }

    suspend fun deleteAlarm(alarm : Alarm){
        dao.deleteAlarm(alarm)
    }

    suspend fun deleteAlarm(){
        dao.deleteAlarm()
    }
}