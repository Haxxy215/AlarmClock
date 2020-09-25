package com.example.alarmclock.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmclock.Repository.AlarmRepository
import java.lang.IllegalArgumentException

class AlarmViewModelFactory(var repository: AlarmRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlarmViewModel::class.java)){
            return AlarmViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}