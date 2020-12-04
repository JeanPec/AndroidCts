package com.shindra.LineTram

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line


class LineTramAdapter(private var lineTramList: List<Line>, private val listener: LineTramViewHolder.OnClickListener)
    : RecyclerView.Adapter<LineTramViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineTramViewHolder {
        // Instantiating the Views in the RecyclerView
        val inflater = LayoutInflater.from(parent.context)
        return LineTramViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LineTramViewHolder, position: Int) {
        // Binding data to each ViewHolder in the RecyclerView
        val lineOfTram = lineTramList[position]
        holder.onBind(lineOfTram, listener)
    }

    override fun getItemCount(): Int = lineTramList.size

}