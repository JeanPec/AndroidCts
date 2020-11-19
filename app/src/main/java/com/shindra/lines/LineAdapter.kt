package com.shindra.lines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineAdapter(var lines: ArrayList<Line>, private val callback: LineViewHolder.OnLineClickListener) : RecyclerView.Adapter<LineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_card_view, parent, false)
        return LineViewHolder(view)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        //set LineViewHolder data
        val line = lines[position]
        holder.onBind(line,callback)
    }

    override fun getItemCount(): Int {
        return lines.size
    }
}