package com.shindra

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class Tram_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tramIcon: ImageView = itemView.findViewById(R.id.image_tram)
    val scheduleButton: Button = itemView.findViewById(R.id.schedule_btn)

    fun onBind(line: Line, callback: OnLineClickListener) {
        scheduleButton.setOnClickListener{
            callback.onItemClick(line)
        }
    }

    interface OnLineClickListener{
        fun onItemClick(line: Line)
    }
}