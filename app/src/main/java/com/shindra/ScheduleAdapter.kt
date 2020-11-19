package com.shindra

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

class ScheduleAdapter(var lineStops: List<Stop>, var lineTramName: String)
    : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        Log.i("CREATE", "stop.name.toString()")

        val inflater = LayoutInflater.from(parent.context)
        return ScheduleViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        Log.i("BIND", "stop.name.toString()")

        val stop = lineStops[position]

        holder.onBind(stop, lineTramName)
    }

    override fun getItemCount(): Int {
        return lineStops.size
    }

}