package com.shindra

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.time.format.DateTimeFormatter
import java.util.*

class TimetableViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val TAG = "timetableViewHolder"

    var stopName: TextView
    var lineName: TextView
    var timeView: TextView
    var nextStart: TextView


    fun onBind(s: Stop) {
        //Log.d(TAG, "onBind")
        //affect the value in s to the views contained in this this holder
        stopName.setText(s.name)
        lineName.setText("test")
        timeView.setText(GetSimpleTimeStringFromDate(s.estimatedDepartureTime))
        nextStart.setText("test2")
    }

    init {
        //Log.d(TAG, "constructor")
        stopName = itemView.findViewById(R.id.cvTimetable_stopName)
        lineName = itemView.findViewById(R.id.cvTimetable_lineName)
        timeView = itemView.findViewById(R.id.cvTimetable_time)
        nextStart = itemView.findViewById(R.id.cvTimetable_nextStart)
    }

    fun GetSimpleTimeStringFromDate(d : Date?) : String {
        if(d == null) return "ND"

        val calendar = Calendar.getInstance()
        calendar.time = d

        return calendar.get(Calendar.HOUR_OF_DAY).toString()+"h"+calendar.get(Calendar.MINUTE).toString()
    }
}