package com.shindra

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

class TimetableRecyclerViewAdaptater internal constructor(private val stops: List<Stop>) : RecyclerView.Adapter<TimetableViewHolder>() {
    private val TAG = "timetableAdaptater"

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        //Log.d(TAG, "onCreateViewHolder")
        val timetable = LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_timetable, parent, false)
        return TimetableViewHolder(timetable)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(receivingHolder: TimetableViewHolder, position: Int) {
        //Log.d(TAG, "onBindViewHolder")
        val requestedCard = stops[position]
        receivingHolder.onBind(requestedCard)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return stops.size
    }
}