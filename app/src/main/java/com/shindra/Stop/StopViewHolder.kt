package com.shindra.Stop

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.Service.Converter
import com.shindra.ctslibrary.bo.Stop

class StopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var stopName: TextView = itemView.findViewById(R.id.stopName)
    var lineName: TextView = itemView.findViewById(R.id.lineName)
    var stopTime: TextView = itemView.findViewById(R.id.stopTime)
    var information: TextView = itemView.findViewById(R.id.information)

    fun onBind(stop : Stop, line : String?)
    {
        stopName.text = stop.name
        lineName.text = "Ligne $line"
        lineName.setTextColor(ContextCompat.getColor(lineName.context, Converter.lineLetterToLineColor(line)))
        stopTime.text = Converter.dateToTime(stop.estimatedDepartureTime)
        information.text = "Prochain départ"
    }
}