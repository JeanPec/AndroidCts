package com.shindra

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop
import java.text.SimpleDateFormat
import java.util.*

class TimetableViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val logTag = "timetableViewHolder"

    private var stopName: TextView
    private var lineName: TextView
    private var timeView: TextView
    private var nextStart: TextView


    fun onBind(c: Context, s: Stop, name: String) {
        //Log.d(TAG, "onBind")
        //affect the value in s to the views contained in this this holder
        stopName.text = s.name
        timeView.text = getSimpleTimeStringFromDate(s.estimatedDepartureTime)
        lineName.text = c.getString(R.string.LineNamePrefix, name)
        lineName.setTextColor(c.getColor(GetColorIDFromLine(name)))
    }

    init {
        //Log.d(TAG, "constructor")
        stopName = itemView.findViewById(R.id.cvTimetable_stopName)
        lineName = itemView.findViewById(R.id.cvTimetable_lineName)
        timeView = itemView.findViewById(R.id.cvTimetable_time)
        nextStart = itemView.findViewById(R.id.cvTimetable_nextStart)
    }

    private fun getSimpleTimeStringFromDate(d : Date?) : String {
        if(d == null) return "-"

        var sdf = SimpleDateFormat("h'h'mm")
        return sdf.format(d)
    }
}