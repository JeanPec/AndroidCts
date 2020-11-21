package com.shindra

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class Schedule_RecyclerViewAdaptater internal constructor(private val tramlineWithTimetable: Line, private val context: Context) : RecyclerView.Adapter<Schedule_ViewHolder>() {
    private val logTag = "timetableAdaptater"

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Schedule_ViewHolder {
        //Log.d(TAG, "onCreateViewHolder")
        val timetable = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_cardview, parent, false)
        return Schedule_ViewHolder(timetable as TextView)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(receivingHolder: Schedule_ViewHolder, position: Int) {
        //Log.d(TAG, "onBindViewHolder")
        val requestedCard = tramlineWithTimetable.stops?.get(position)
        receivingHolder.onBind(context, requestedCard!!, tramlineWithTimetable.name)

    }

    // total number of rows
    override fun getItemCount(): Int {
        return tramlineWithTimetable.stops!!.size // Je  n'ai pas reussi à le faire avec des ?, apparament Int? ne permet pas le override
    }
}