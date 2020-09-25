package com.example.alarmclock.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [ Alarm::class] , version = 1)
abstract class AlarmDatabase : RoomDatabase(){

    abstract fun getAlarmDAO() : AlarmDAO

    companion object{

        @Volatile private var instance : AlarmDatabase ? = null

        operator fun invoke(context : Context) = instance ?: synchronized(Any()){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AlarmDatabase::class.java,
            "alarmDatabase"
        ).build()

    }
}