package com.example.alarmclock.ViewModel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmclock.Database.Alarm
import com.example.alarmclock.Repository.AlarmRepository
import kotlinx.coroutines.launch
import java.util.*

class AlarmViewModel( var alarmRepository: AlarmRepository) : ViewModel(){

    var alarms = alarmRepository.alarms


    fun setAlarm(alarm: Alarm) = viewModelScope.launch {
        alarmRepository.setAlarm(alarm)
    }

    fun updateAlarm(alarm: Alarm) = viewModelScope.launch {
        alarmRepository.updateAlarm(alarm)
    }

    fun deleteAlarm(alarm: Alarm) = viewModelScope.launch {
        alarmRepository.deleteAlarm(alarm)
    }

    fun deleteAlarm() = viewModelScope.launch {
        alarmRepository.deleteAlarm()
    }
}