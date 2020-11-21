package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import java.util.*

class Tram_Adapter(var lines: ArrayList<Line>, val callback: Tram_ViewHolder.OnLineClickListener) : RecyclerView.Adapter<Tram_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tram_ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.tram_cardview, parent, false)
        return Tram_ViewHolder(lineView)
    }
    private fun getTramImage(line: Line) : Int {
        return when (line.name){
            "A" -> R.drawable.tram_a
            "B" -> R.drawable.tram_b
            "C" -> R.drawable.tram_c
            "D" -> R.drawable.tram_d
            "E" -> R.drawable.tram_e
            "F" -> R.drawable.tram_f
            else -> R.drawable.tram
        }
    }

    override fun getItemCount(): Int {
        return lines.size
    }
    override fun onBindViewHolder(holder: Tram_ViewHolder, position: Int) {
        holder.tramIcon.setImageResource(getTramImage(lines[position]))
        holder.onBind(lines[position],callback)
    }


}