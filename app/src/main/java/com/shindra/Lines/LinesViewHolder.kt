package com.shindra.Lines

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ScheduleClick
import com.shindra.ctslibrary.bo.Line

class LinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mImageView: ImageView = itemView.findViewById(R.id.tramLine)
    private var scheduleButton = itemView.findViewById<Button>(R.id.schedule_button)

    fun onBind(line: Line, callback: ScheduleClick) {
        scheduleButton.setOnClickListener { callback.onScheduleClick(line) }

        when (line.name) {
            itemView.context.getString(R.string.line_id_A) -> mImageView.setImageResource(R.drawable.tram_a)
            itemView.context.getString(R.string.line_id_B) -> mImageView.setImageResource(R.drawable.tram_b)
            itemView.context.getString(R.string.line_id_C) -> mImageView.setImageResource(R.drawable.tram_c)
            itemView.context.getString(R.string.line_id_D) -> mImageView.setImageResource(R.drawable.tram_d)
            itemView.context.getString(R.string.line_id_E) -> mImageView.setImageResource(R.drawable.tram_e)
            itemView.context.getString(R.string.line_id_F) -> mImageView.setImageResource(R.drawable.tram_f)
            else -> mImageView.setImageResource(R.drawable.tram)
        }
    }

}