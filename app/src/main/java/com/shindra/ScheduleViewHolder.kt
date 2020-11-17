package com.shindra

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textHeadline: TextView = itemView.findViewById(R.id.text_headline)
    val textLine: TextView = itemView.findViewById(R.id.text_line)
    val textHour: TextView = itemView.findViewById(R.id.text_hour)
    val textBody: TextView = itemView.findViewById(R.id.text_body)
}