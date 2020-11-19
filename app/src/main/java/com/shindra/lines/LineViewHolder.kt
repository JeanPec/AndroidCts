package com.shindra.lines

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Line

class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tramIcon: ImageView = itemView.findViewById(R.id.image_tram_icon)
    private val scheduleButton: Button = itemView.findViewById(R.id.button_schedule)

    fun onBind(line: Line, callback: OnLineClickListener) {
        //set LineViewHolder data
        tramIcon.setImageResource(getTramIcon(line))

        //set Button listener
        scheduleButton.setOnClickListener{
            callback.onItemClick(line)
        }
    }

    //callback interface
    interface OnLineClickListener{
        fun onItemClick(line: Line)
    }

    private fun getTramIcon(line: Line) : Int {
        return when (line.name){
            itemView.context.getString(R.string.line_a) -> R.drawable.tram_a
            itemView.context.getString(R.string.line_b) -> R.drawable.tram_b
            itemView.context.getString(R.string.line_c) -> R.drawable.tram_c
            itemView.context.getString(R.string.line_d) -> R.drawable.tram_d
            itemView.context.getString(R.string.line_e) -> R.drawable.tram_e
            itemView.context.getString(R.string.line_f) -> R.drawable.tram_f
            else -> R.drawable.tram
        }
    }
}