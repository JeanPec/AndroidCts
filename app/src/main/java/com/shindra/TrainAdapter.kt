package com.shindra

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.convertTo
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line


class TrainAdapter(var lineList: ArrayList<Line>, val callback: TrainViewHolder.RecyclerLineClick) : RecyclerView.Adapter<TrainAdapter.TrainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.nos_trams,
        parent, false)
        return TrainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val currentLine : Line = lineList[position]
        holder.onBind(currentLine,callback,currentLine.name)
    }

    override fun getItemCount() = lineList.size


    class TrainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        private val button: Button = itemView.findViewById(R.id.button_horraires)

        fun onBind(line : Line,callback : RecyclerLineClick, nameLine : String) {
            imageView.setImageResource(getImagesRessource(nameLine))
            button.setOnClickListener{
                callback.onLineClick(line)
            }
        }
        interface RecyclerLineClick{
            fun onLineClick(line :Line)
        }
    }
}
private fun getImagesRessource(line_name : String) : Int{
    return when (line_name){
        "A" -> R.drawable.tram_a
        "B" -> R.drawable.tram_b
        "C" -> R.drawable.tram_c
        "D" -> R.drawable.tram_d
        "E" -> R.drawable.tram_e
        "F" -> R.drawable.tram_f
        else -> {
            R.drawable.tram
        }


    }
}