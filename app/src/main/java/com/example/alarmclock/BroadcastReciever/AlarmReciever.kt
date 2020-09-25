package com.example.alarmclock.BroadcastReciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        var getString : String = intent!!.getStringExtra("extra")

        var service_intent : Intent = Intent(context , RingtoneService::class.java)
        service_intent.putExtra("extra" , getString)
        context!!.startService(service_intent)
    }
}