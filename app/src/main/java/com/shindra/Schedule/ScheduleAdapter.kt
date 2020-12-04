package com.shindra.Schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop

class ScheduleAdapter(var lineStops: List<Stop>, var lineTramName: String)
    : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        // Instantiating the Views in the RecyclerView
        val inflater = LayoutInflater.from(parent.context)
        return ScheduleViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        // Binding data to each ViewHolder in the RecyclerView
        holder.onBind(lineStops[position], lineTramName)
    }

    override fun getItemCount(): Int = lineStops.size

}