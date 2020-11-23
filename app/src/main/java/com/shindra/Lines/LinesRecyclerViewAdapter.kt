package com.shindra.Lines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ScheduleClick
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LinesRecyclerViewAdapter(var lines: ArrayList<Line>, val callback: ScheduleClick) : RecyclerView.Adapter<LinesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.line_cardview, parent, false)
        return LinesViewHolder(v)
    }

    override fun onBindViewHolder(holder: LinesViewHolder, position: Int) {
        val currentItem = lines[position]
        holder.onBind(currentItem, callback)
    }

    override fun getItemCount(): Int {
        return lines.size
    }
}