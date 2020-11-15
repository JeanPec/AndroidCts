package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop
import java.text.SimpleDateFormat

class ScheduleAdapter(private val line: String, private val stops: ArrayList<Stop>, private val context: FragmentActivity?) : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.schedule_card_view, parent, false)
        return ScheduleViewHolder(lineView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val stop = stops[position]
        holder.textHeadline.text = stop.name
        holder.textLine.text = line
        holder.textHour.text = SimpleDateFormat("HH").format(stop.estimatedDepartureTime?.time)+context?.getString(R.string.hour_unit)+SimpleDateFormat("mm").format(stop.estimatedDepartureTime?.time)
        holder.textBody.text = context?.getString(R.string.schedule_card_view_body)
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return stops.size
    }
}