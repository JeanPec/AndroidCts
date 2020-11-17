package com.shindra.Lines

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ScheduleClick
import com.shindra.ctslibrary.bo.Line

class LinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mImageView: ImageView = itemView.findViewById(R.id.tramLine)
    private var scheduleButton = itemView.findViewById<Button>(R.id.schedule_button)

    fun onBind(line: Line, callback: ScheduleClick) {
        scheduleButton.setOnClickListener { callback.onScheduleClick(line) }
    }

}