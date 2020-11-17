package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineAdapter(var lines: ArrayList<Line>, val callback: Tram_ViewHolder.OnLineClickListener) : RecyclerView.Adapter<Tram_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tram_ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.tram_cardview, parent, false)
        return Tram_ViewHolder(lineView)
    }
    private fun getTramImage(line: Line) : Int {
        return when (line.name){
            "Parc des Sports - Illkirch Graffenstaden" -> R.drawable.tram_a
            "Lingolsheim Tiergaertel - Hoenheim Gare" -> R.drawable.tram_b
            "Gare Centrale - Neuhof Rodolphe Reuss" -> R.drawable.tram_c
            "Poteries - Port du Rhin / Kehl Rathaus" -> R.drawable.tram_d
            "Robertsau l'Escale - Campus d'Illkirch" -> R.drawable.tram_e
            "Comtes - Place d'Islande" -> R.drawable.tram_f
            else -> R.drawable.tram
        }
    }

    override fun getItemCount(): Int {
        return lines.size
    }
    override fun onBindViewHolder(holder: Tram_ViewHolder, position: Int) {
        val line = lines[position]
        holder.tramIcon.setImageResource(getTramImage(line))
        holder.onBind(line,callback)
    }


}