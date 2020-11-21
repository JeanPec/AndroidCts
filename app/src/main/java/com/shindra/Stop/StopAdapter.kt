package com.shindra.Stop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.Service.Converter
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class StopAdapter(var stops: ArrayList<Stop>, var lineName: String?) : RecyclerView.Adapter<StopViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_stop, parent, false)
        return StopViewHolder(view)
    }

    override fun onBindViewHolder(holder: StopViewHolder, position: Int)
    {
        val (name, _, estimatedDepartureTime) = stops[position]
        holder.stopName.text = name
        holder.lineName.text = "Ligne $lineName"
        holder.lineName.setTextColor(ContextCompat.getColor(holder.lineName.context, Converter.lineLetterToLineColor(lineName)))
        holder.stopTime.text = Converter.dateToTime(estimatedDepartureTime)
        holder.information.text = "Prochain départ"
    }

    override fun getItemCount(): Int
    {
        return stops.size
    }
}