package com.example.alarmclock.Adapter

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclock.AddAlarm
import com.example.alarmclock.Database.Alarm
import com.example.alarmclock.R
import com.example.alarmclock.ViewModel.AlarmViewModel

class AlarmAdapter(var alarmList : List<Alarm> ,var  context: Context , var alarmViewModel: AlarmViewModel) :
    RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.alarm_layout , parent , false))
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.hours.text = if (alarmList.get(position).hours < 9) "0" + alarmList.get(position).hours.toString() + " : " else alarmList.get(position).hours.toString() + " : "
        holder.minutes.text = if (alarmList.get(position).minutes < 9) "0" + alarmList.get(position).minutes.toString() else alarmList.get(position).minutes.toString()
        holder.amPm.text = alarmList.get(position).dayNight

        holder.delete.setOnClickListener {
            alarmViewModel.deleteAlarm(alarmList.get(position))
            Log.d("Hello" , "AlarmList" + alarmList.get(position))
        }

        holder.edit.setOnClickListener {

            context.startActivity(Intent(context , AddAlarm::class.java).putExtra("Hours" , alarmList.get(position).hours).putExtra("Minutes" , alarmList.get(position).minutes).putExtra("AMPM" , alarmList.get(position).dayNight).putExtra("id" , alarmList.get(position).id))
        }


    }

    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){

        var hours = itemView.findViewById(R.id.hours) as TextView
        var minutes = itemView.findViewById(R.id.minutes) as TextView
        var amPm = itemView.findViewById(R.id.amPm) as TextView
        var delete  = itemView.findViewById(R.id.delete) as TextView
        var edit  = itemView.findViewById(R.id.edit) as TextView


    }
}