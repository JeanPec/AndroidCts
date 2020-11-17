package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.TramlineViewHolder.RecyclerItemClick
import com.shindra.ctslibrary.bo.Line

class TramlinesRecyclerViewAdapter  // data is passed into the constructor
internal constructor(private val availableTramlines: List<Line>, private val callback: RecyclerItemClick) : RecyclerView.Adapter<TramlineViewHolder>() {
    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramlineViewHolder {
        val tramline = LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_tramline, parent, false)
        return TramlineViewHolder(tramline)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(receivingHolder: TramlineViewHolder, position: Int) {
        val requestedTramline = availableTramlines[position]
        receivingHolder.onBind(requestedTramline, callback)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return availableTramlines.size
    }
}