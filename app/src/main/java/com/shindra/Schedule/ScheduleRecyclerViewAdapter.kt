package com.shindra.Schedule

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import java.util.*


class ScheduleRecyclerViewAdapter(var stops: ArrayList<Stop>, private val lineID: String) : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.schedule_cardview, parent, false)
        return ScheduleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val stop = stops[position]
        holder.onBind(stop, lineID)
    }

    override fun getItemCount(): Int {
        return stops.size
    }
}



