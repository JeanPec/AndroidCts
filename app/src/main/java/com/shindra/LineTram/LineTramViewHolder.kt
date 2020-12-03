package com.shindra.LineTram

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.Utilities.ApiLinesConvertor
import com.shindra.R

class LineTramViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.line_tram, parent, false)) {

    interface OnClickListener{
        fun onClick(lineTram: Line)
    }

    var iconTram: ImageView = itemView.findViewById(R.id.iconTram)
    var btnSchedule: Button = itemView.findViewById(R.id.btnSchedule)

    fun onBind(lineTram: Line, listener: OnClickListener) {
        iconTram.setImageResource(ApiLinesConvertor().apiLineToIcon(lineTram))
        btnSchedule.setOnClickListener {listener.onClick(lineTram)}
    }
}

