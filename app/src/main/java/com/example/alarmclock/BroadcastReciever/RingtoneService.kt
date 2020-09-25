package com.example.alarmclock.BroadcastReciever

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.alarmclock.MainActivity
import com.example.alarmclock.R

class RingtoneService : Service() {

    companion object{
        lateinit var ringtone : Ringtone
    }

    var id : Int  = 0
    var isRunning : Boolean = false
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val state = intent!!.getStringExtra("extra")

        when(state){

            "on" -> id = 1
            "off"-> id= 0
        }

        if(!this.isRunning && id == 1){

            playAlarm()
            this.isRunning = true
            this.id = 0
            fireNotification()
        }else if(this.isRunning && id ==0){

            ringtone.stop()
            this.isRunning = false
            this.id = 0
        }else if (!this.isRunning && id ==0){
            this.isRunning = false
            this.id = 0

        }else if( this.isRunning && id == 1){

            this.isRunning = true
            this.id = 1
        }else{

        }

        return START_NOT_STICKY
    }

    private fun fireNotification(){
        var intent : Intent = Intent(this , MainActivity::class.java)
        var pendingIntent : PendingIntent = PendingIntent.getActivity(this , 0 , intent , 0)
        var defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notification : Notification = NotificationCompat.Builder(this)
            .setContentTitle("Alarm is Going Off")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setSound(defaultSoundUri)
            .setContentText("Click me")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(0 , notification)
    }
    private fun playAlarm(){
        var alarmUri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        if (alarmUri == null)
            alarmUri =  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        ringtone = RingtoneManager.getRingtone(baseContext , alarmUri)
        ringtone.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.isRunning = false
    }
}