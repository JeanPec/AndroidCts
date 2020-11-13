package com.shindra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class TrainAdapter(private val trainList: List<Train>) : RecyclerView.Adapter<TrainAdapter.TrainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.nos_trams,
        parent, false)
        return TrainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val currentItem = trainList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
    }

    override fun getItemCount() = trainList.size


    class TrainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image_view)

    }
}