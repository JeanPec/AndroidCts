package com.shindra

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class TimetableViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val logTag = "timetableViewHolder"

    private var stopName: TextView
    private var lineName: TextView
    private var timeView: TextView
    private var nextStart: TextView


    fun onBind(c: Context, s: Stop, name: String) {

        stopName.text = s.name
        lineName.text = c.getString(R.string.Schedule_LineName, name)
        timeView.text = getSimpleTimeStringFromDate(s.estimatedDepartureTime)
    }

    init {
        lineName = itemView.findViewById(R.id.schedule_linename)
        stopName = itemView.findViewById(R.id.schedule_stopname)
        nextStart = itemView.findViewById(R.id.schedule_nextdeparture)
        timeView = itemView.findViewById(R.id.schedule_hour)
    }

    private fun getSimpleTimeStringFromDate(d : Date?) : String {
        if(d == null) return "ND"

        val calendar = Calendar.getInstance()
        calendar.time = d

        return calendar.get(Calendar.HOUR_OF_DAY).toString()+"h"+calendar.get(Calendar.MINUTE).toString()
    }
}