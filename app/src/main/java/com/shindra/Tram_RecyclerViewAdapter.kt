package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class Tram_RecyclerViewAdapter
internal constructor(private val availableTramlines: List<Line>, private val callback: (Line) -> Unit) : RecyclerView.Adapter<Tram_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tram_ViewHolder {
        val tramline = LayoutInflater.from(parent.context)
                .inflate(R.layout.tram_cardview, parent, false)
        return Tram_ViewHolder(tramline)
    }

    override fun onBindViewHolder(receivingHolder: Tram_ViewHolder, position: Int) {
        val requestedTramline = availableTramlines[position]

    }

    override fun getItemCount(): Int {
        return availableTramlines.size
    }
}
