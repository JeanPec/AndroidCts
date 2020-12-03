package com.shindra.schedule

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.HelperLine
import com.shindra.R

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Attributes
    private val mNextDepartureTime: TextView = itemView.findViewById(R.id.cv_schedule_textNextDepartureTime)
    private val mTramStationName: TextView = itemView.findViewById(R.id.cv_schedule_textTramStation)
    private val mTramLineLetter: TextView = itemView.findViewById(R.id.cv_schedule_textTramLineLetter)

    // Methods
    fun onBind(item: ScheduleItem, context: Context){
        val tramLineText = "${context.getString(R.string.line)} ${item.mTramLineLetter}"

        mTramStationName.text = item.mTramStationName
        mNextDepartureTime.text = item.mNextDepartureTime
        mTramLineLetter.text = tramLineText
        mTramLineLetter.setTextColor(context.getColor(HelperLine.getLineColor(item.mTramLineLetter)))
    }
}