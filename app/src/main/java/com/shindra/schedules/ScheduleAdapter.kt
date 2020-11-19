package com.shindra.schedules

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import kotlin.collections.ArrayList

class ScheduleAdapter(private val line: String, var stops: ArrayList<Stop>, private val context: FragmentActivity?) : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_card_view, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        //set ScheduleViewHolder data
        val stop = stops[position]
        holder.onBind(line, stop)
    }

    override fun getItemCount(): Int {
        return stops.size
    }
}