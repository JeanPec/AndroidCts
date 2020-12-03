package com.shindra

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

//lateinit var callback: ViewHolderTram.RecyclerItemClick


class RecyclerTramAdapter(private val listetram: ArrayList<Line>, private val callback: ViewHolderTram.RecyclerItemClick) : RecyclerView.Adapter<ViewHolderTram>() {
    override fun onBindViewHolder(holder: ViewHolderTram, position: Int) {
        holder.onBind(listetram[position], callback)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTram {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tram, parent, false)
        return ViewHolderTram(v)
    }
    override fun getItemCount(): Int {
        return listetram.size
    }
}