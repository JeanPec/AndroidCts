package com.shindra.Schedule

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import com.shindra.getDateStringHHMM


class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var scheduleStop: TextView = itemView.findViewById(R.id.Schedule_stop)
    var scheduleLine: TextView = itemView.findViewById(R.id.Schedule_line)
    var scheduleTime: TextView = itemView.findViewById(R.id.Schedule_time)

    fun onBind(stop: Stop, lineID: String) {
        scheduleStop.text = stop.name
        val lineText = itemView.context.getString(R.string.activity_schedule_line_name) + lineID
        scheduleLine.text = lineText
        when (lineID) {
            itemView.context.getString(R.string.line_id_A) -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_A))
            itemView.context.getString(R.string.line_id_B) -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_B))
            itemView.context.getString(R.string.line_id_C) -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_C))
            itemView.context.getString(R.string.line_id_D) -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_D))
            itemView.context.getString(R.string.line_id_E) -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_E))
            itemView.context.getString(R.string.line_id_F) -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.ligne_F))
            else -> scheduleLine.setTextColor(ContextCompat.getColor(scheduleLine.context, R.color.Body2))
        }
        scheduleTime.text = stop.estimatedArrivalTime?.getDateStringHHMM()
    }
}


