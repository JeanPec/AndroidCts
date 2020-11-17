package com.shindra.Schedule

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var scheduleStop = itemView.findViewById<TextView>(R.id.Schedule_stop)
    var scheduleLine = itemView.findViewById<TextView>(R.id.Schedule_line)
    var scheduleTime = itemView.findViewById<TextView>(R.id.Schedule_time)




    fun onBind(stop: Stop?) {
        //lineName.setText(line.na);
    }
}