package com.example.alarmclock.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alarm(
    var hours : Int,
    var minutes : Int,
    var dayNight : String,
    var status : String,
    var label : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}