package com.shindra

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop

class ScheduleViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.schedule, parent, false)) {

    var textStop: TextView = itemView.findViewById(R.id.stopName)
    var textLineTram: TextView = itemView.findViewById(R.id.lineTramName)
    var textStopHour: TextView = itemView.findViewById(R.id.stopHour)

    fun onBind(stop: Stop, lineTramName: String) {
        textStop.text = (stop.name)
        textLineTram.text = lineTramName
        textLineTram.setTextColor(ContextCompat.getColor(textLineTram.context, ApiLinesConvertor().lineToColor(lineTramName)))
        textStopHour.text = ApiLinesConvertor().getDateText(stop.estimatedArrivalTime)
    }
}