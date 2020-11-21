package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.ctslibrary.bo.Line
import java.util.ArrayList


class LineTramAdapter(private var lineTramList: List<Line>, private val listener: LineTramViewHolder.OnClickListener)
    : RecyclerView.Adapter<LineTramViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineTramViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LineTramViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LineTramViewHolder, position: Int) {
        val lineOfTram = lineTramList[position]
        holder.iconTram.setImageResource(ApiLinesConvertor().apiLineToIcon(lineOfTram))
        //holder.initializeCardView(lineOfTram)
        holder.onBind(lineOfTram, listener)
    }

    override fun getItemCount(): Int = lineTramList.size

}