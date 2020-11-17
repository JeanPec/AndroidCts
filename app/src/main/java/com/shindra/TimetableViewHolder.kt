package com.shindra

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

class TimetableViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val TAG = "timetableViewHolder"

    var stopName: TextView
    var lineName: TextView
    var time: TextView
    var nextStart: TextView


    fun onBind(s: Stop) {
        //affect the value in s to the views contained in this this holder
        stopName.setText(s.name)
        lineName.setText("test")
        time.setText(s.estimatedDepartureTime.toString())
        nextStart.setText("test2")
    }

    init {
        Log.i(TAG, "constructor")
        stopName = itemView.findViewById(R.id.cvTimetable_stopName)
        lineName = itemView.findViewById(R.id.cvTimetable_lineName)
        time = itemView.findViewById(R.id.cvTimetable_time)
        nextStart = itemView.findViewById(R.id.cvTimetable_time)
    }
}