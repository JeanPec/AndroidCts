package com.shindra.Stop

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R

class StopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var stopName: TextView = itemView.findViewById(R.id.stopName)
    var lineName: TextView = itemView.findViewById(R.id.lineName)
    var stopTime: TextView = itemView.findViewById(R.id.stopTime)
    var information: TextView = itemView.findViewById(R.id.information)
}