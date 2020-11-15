package com.shindra

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tramIcon: ImageView = itemView.findViewById(R.id.image_tram_icon)
    val tramImage: ImageView = itemView.findViewById(R.id.image_tram_image)
    val scheduleButton: Button = itemView.findViewById(R.id.button_schedule)

    fun onBind(line: Line, callback: OnLineClickListener) {
        scheduleButton.setOnClickListener{
            callback.onItemClick(line)
        }
    }

    interface OnLineClickListener{
        fun onItemClick(line: Line)
    }
}
