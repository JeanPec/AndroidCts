package com.shindra

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ApiLinesConvertor

class LineTramViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.line_tram, parent, false)) {

    interface OnClickListener{
        fun onClick(lineTram: Line)
    }

    var iconTram: ImageView = itemView.findViewById(R.id.iconTram)
    var imagetram: ImageView = itemView.findViewById(R.id.imageTram)
    var btnSchedule: Button = itemView.findViewById(R.id.btnSchedule)

    fun onBind(lineTram: Line, listener: OnClickListener) {
        iconTram.setImageResource(ApiLinesConvertor().apiLineToIcon(lineTram))
        // It's {} not () ...
        btnSchedule.setOnClickListener {listener.onClick(lineTram)}
    }
}

