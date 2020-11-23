package com.shindra.Schedule

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var scheduleStop: TextView = itemView.findViewById(R.id.Schedule_stop)
    var scheduleLine: TextView = itemView.findViewById(R.id.Schedule_line)
    var scheduleTime: TextView = itemView.findViewById(R.id.Schedule_time)

    fun onBind(stop: Stop, lineID: String) {
        scheduleStop.text = stop.name
        scheduleLine.text = itemView.context.getString(R.string.activity_schedule_line_name) + lineID
        when (lineID) {
            "A" -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_A))
            "B" -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_B))
            "C" -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_C))
            "D" -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_D))
            "E" -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_E))
            "F" -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_F))
            else -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.Body2))
        }
        scheduleTime.text = stop.estimatedArrivalTime?.getDateStringHHMM()
    }
}


fun Date.getDateStringHHMM(): String {
    val date: Date = this // your date
    val cal = Calendar.getInstance()
    cal.time = date
    val hours = cal.get(Calendar.HOUR_OF_DAY).toString()
    val minutes = String.format("%02d", cal.get(Calendar.MINUTE))
    return (hours + "h" + minutes)
}