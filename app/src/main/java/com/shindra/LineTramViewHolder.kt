package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class LineTramViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.line_tram, parent, false)) {

    private var iconTram: ImageView
    private var imagetram: ImageView? = null
    private var btnSchedule: Button? = null

    init {
        iconTram = itemView.findViewById(R.id.iconTram)
        imagetram = itemView.findViewById(R.id.imageTram)
        btnSchedule = itemView.findViewById(R.id.btnSchedule)
    }

    fun initializeCardView(lineTram: Line) {
        // "switch" is "when" in Kotlin
        when(lineTram.name) {
            "A" -> iconTram.setImageResource(R.drawable.ic_tram_a)
            "B" -> iconTram.setImageResource(R.drawable.ic_tram_b)
            "C" -> iconTram.setImageResource(R.drawable.ic_tram_c)
            "D" -> iconTram.setImageResource(R.drawable.ic_tram_d)
            "E" -> iconTram.setImageResource(R.drawable.ic_tram_e)
            "F" -> iconTram.setImageResource(R.drawable.ic_tram_f)
        }
    }
}

