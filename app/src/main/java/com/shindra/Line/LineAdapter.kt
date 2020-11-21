package com.shindra.Line

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.Service.Converter
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineAdapter(var lines: ArrayList<Line>, val callback: ILineClickable) : RecyclerView.Adapter<LineViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_line, parent, false)
        return LineViewHolder(view)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int)
    {
        val item = lines[position]
        holder.onBind(item, callback)
    }

    override fun getItemCount(): Int
    {
        return lines.size
    }
}