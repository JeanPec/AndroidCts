package com.shindra.Schedule

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
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
        when (lineID) {
            "A" -> holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_A))
            "B" -> holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_B))
            "C" -> holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_C))
            "D" -> holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_D))
            "E" -> holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_E))
            "F" -> holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.ligne_F))
            else -> holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.context, R.color.Body2))
        }
        holder.scheduleTime.text = stop.estimatedArrivalTime?.getDateStringHHMM()
    }

    override fun getItemCount(): Int {
        return stops.size
    }


}

fun Date.getDateStringHHMM(): String {
    val date:Date = this // your date
    val cal = Calendar.getInstance()
    cal.time = date
    val hours = cal.get(Calendar.HOUR_OF_DAY).toString()
    val minutes = String.format("%02d", cal.get(Calendar.MINUTE))
    return (hours + "h" + minutes)
}


