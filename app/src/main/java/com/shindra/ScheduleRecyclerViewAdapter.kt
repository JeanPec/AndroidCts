package com.shindra

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class TimetableRecyclerViewAdaptater internal constructor(private val tramlineWithTimetable: Line, private val context: Context) : RecyclerView.Adapter<TimetableViewHolder>() {
    private val logTag = "timetableAdaptater"

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        //Log.d(TAG, "onCreateViewHolder")
        val timetable = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_cardview, parent, false)
        return TimetableViewHolder(timetable)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(receivingHolder: TimetableViewHolder, position: Int) {
        //Log.d(TAG, "onBindViewHolder")
        val requestedCard = tramlineWithTimetable.stops?.get(position)
        receivingHolder.onBind(context, requestedCard!!, tramlineWithTimetable.name)

    }

    // total number of rows
    override fun getItemCount(): Int {
        return tramlineWithTimetable.stops!!.size
    }
}