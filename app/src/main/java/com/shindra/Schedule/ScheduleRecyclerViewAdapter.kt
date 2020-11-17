package com.shindra.Schedule

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.MapClick
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import java.util.*


class ScheduleRecyclerViewAdapter(var stops: ArrayList<Stop>, private val lineID: String) : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.schedule_cardview, parent, false)

        return ScheduleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val stop = stops[position]
        holder.scheduleStop.text = stop.name
        holder.scheduleLine.text = "Ligne $lineID"
        if (lineID == "A") {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_A))
        } else if (lineID == "B") {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_B))
        } else if (lineID == "C") {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_C))
        } else if (lineID == "D") {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_D))
        } else if (lineID == "E") {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_E))
        } else if (lineID == "F") {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_F))
        } else {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.Body2))
        }
        holder.scheduleTime.text = convert(stop.estimatedArrivalTime)
    }

    override fun getItemCount(): Int {
        return stops.size
    }

    companion object {
        fun convert(input: Date?): String {
            return if (input != null) {
                val hours = input.hours
                String.format("%dh%02d", hours, input.minutes)
            } else {
                ""
            }
        }
    }
}


