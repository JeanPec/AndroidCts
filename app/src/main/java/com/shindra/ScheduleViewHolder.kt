package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop

class ScheduleViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.schedule, parent, false)) {

    var textStop: TextView = itemView.findViewById(R.id.stopName)
    var textLineTram: TextView = itemView.findViewById(R.id.lineTramName)
    var textStopHour: TextView = itemView.findViewById(R.id.stopHour)
    var textInformation: TextView = itemView.findViewById(R.id.stopInformation)

    fun onBind(stop: Stop) {
        textStop.setText("stpo")
        textLineTram.setText("stop")
        textStopHour.setText("stop")
        textInformation.setText("stop")
    }
}