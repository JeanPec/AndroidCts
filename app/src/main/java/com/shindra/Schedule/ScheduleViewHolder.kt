package com.shindra.Schedule

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Others.HelperLine
import com.shindra.R

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Attributes
    private var mNextDepartureTime: TextView = itemView.findViewById(R.id.cv_schedule_textNextDepartureTime)
    private var mTramStationName: TextView = itemView.findViewById(R.id.cv_schedule_textTramStation)
    private var mTramLineLetter: TextView = itemView.findViewById(R.id.cv_schedule_textTramLineLetter)

    // Methods
    fun onBind(item: ScheduleItem, context: Context){
        mTramStationName.setText(item.getTramStationName());
        mNextDepartureTime.setText(item.getNextDepartureTime());
        mTramLineLetter.setText(context.getString(R.string.line) + " " + item.getTramLineLetter());
        mTramLineLetter.setTextColor(context.getColor(HelperLine.GetLineColor(item.getTramLineLetter())))
    }
}