package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineAdapter(private val lines: ArrayList<Line>, val callback: LineViewHolder.OnLineClickListener) : RecyclerView.Adapter<LineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.line_card_view, parent, false)
        return LineViewHolder(lineView)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val line = lines[position]
        holder.tramIcon.setImageResource(getTramIcon(line))
        holder.onBind(line,callback)
    }

    override fun getItemCount(): Int {
        return lines.size
    }

    private fun getTramIcon(line: Line) : Int {
        return when (line.name){
            "Ligne A" -> R.drawable.tram_a
            "Ligne B" -> R.drawable.tram_b
            "Ligne C" -> R.drawable.tram_c
            "Ligne D" -> R.drawable.tram_d
            "Ligne E" -> R.drawable.tram_e
            "Ligne F" -> R.drawable.tram_f
            else -> R.drawable.tram
        }
    }
}