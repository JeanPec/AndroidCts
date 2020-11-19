package com.shindra.Lines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
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
        when (currentItem.name) {
            "A" -> {
                holder.mImageView.setImageResource(R.drawable.tram_a) //currentItem.getImageResource());
            }
            "B" -> {
                holder.mImageView.setImageResource(R.drawable.tram_b) //currentItem.getImageResource());
            }
            "C" -> {
                holder.mImageView.setImageResource(R.drawable.tram_c) //currentItem.getImageResource());
            }
            "D" -> {
                holder.mImageView.setImageResource(R.drawable.tram_d) //currentItem.getImageResource());
            }
            "E" -> {
                holder.mImageView.setImageResource(R.drawable.tram_e) //currentItem.getImageResource());
            }
            "F" -> {
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