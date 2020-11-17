package com.shindra

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerTramAdapter (private val listetram: ArrayList<RecyclerTram>) : RecyclerView.Adapter<RecyclerTramAdapter.ViewHolder>() {

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tram: RecyclerTram = listetram[position]

        holder.viewTram.setImageResource(tram.nom_tram)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tram, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listetram.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var viewTram: ImageView = itemView.findViewById(R.id.tram)
    }

}