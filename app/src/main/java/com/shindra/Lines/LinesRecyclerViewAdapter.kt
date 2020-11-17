package com.shindra.Lines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.RecyclerItemClick
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LinesRecyclerViewAdapter(var lines: ArrayList<Line>, val callback: RecyclerItemClick) : RecyclerView.Adapter<LinesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.line_cardview, parent, false)
        return LinesViewHolder(v)
    }

    override fun onBindViewHolder(holder: LinesViewHolder, position: Int) {
        val currentItem = lines[position]
        when (currentItem.name) {
            "Parc des Sports - Illkirch Graffenstaden" -> {
                holder.mImageView.setImageResource(R.drawable.tram_a) //currentItem.getImageResource());
            }
            "Lingolsheim Tiergaertel - Hoenheim Gare" -> {
                holder.mImageView.setImageResource(R.drawable.tram_b) //currentItem.getImageResource());
            }
            "Gare Centrale - Neuhof Rodolphe Reuss" -> {
                holder.mImageView.setImageResource(R.drawable.tram_c) //currentItem.getImageResource());
            }
            "Poteries - Port du Rhin / Kehl Rathaus" -> {
                holder.mImageView.setImageResource(R.drawable.tram_d) //currentItem.getImageResource());
            }
            "Robertsau l'Escale - Campus d'Illkirch" -> {
                holder.mImageView.setImageResource(R.drawable.tram_e) //currentItem.getImageResource());
            }
            "Comtes - Place d'Islande" -> {
                holder.mImageView.setImageResource(R.drawable.tram_f) //currentItem.getImageResource());
            }
            else -> {
                holder.mImageView.setImageResource(R.drawable.tram) //currentItem.getImageResource());
            }
        }
        holder.onBind(currentItem, callback)
    }

    override fun getItemCount(): Int {
        return lines.size
    }
}