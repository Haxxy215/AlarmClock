package com.example.alarmclock.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlarmDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAlarm(alarm: Alarm)

    @Update
    suspend fun updateAlarm(alarm: Alarm)

    @Delete
    suspend fun deleteAlarm(alarm: Alarm)

    @Query("DELETE  FROM alarm")
    suspend fun deleteAlarm()

    @Query("SELECT * FROM alarm")
    fun getAlarm() : LiveData<List<Alarm>>



}