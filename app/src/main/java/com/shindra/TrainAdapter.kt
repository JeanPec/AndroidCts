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


class TrainAdapter(private val lineList: List<Line>, val callback: TrainViewHolder.RecyclerItemClick) : RecyclerView.Adapter<TrainAdapter.TrainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.nos_trams,
        parent, false)
        return TrainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val currentLine : Line = lineList[position]
        holder.imageView.setImageResource(getImagesRessource(currentLine.name))
        holder.onBind(currentLine,callback)
    }

    override fun getItemCount() = lineList.size


    class TrainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        private val button: Button = itemView.findViewById(R.id.button_horraires)

        fun onBind(line : Line,callback : RecyclerItemClick) {
            button.setOnClickListener{
                callback.onItemClick(line)
            }
        }
        interface RecyclerItemClick{
            fun onItemClick(line :Line)
        }
        }


    }
private fun getImagesRessource(line_name : String) : Int{
    when (line_name){
        "tram_a" -> return R.drawable.tram_a
        "tram_b" -> return R.drawable.tram_b
        "tram_c" -> return R.drawable.tram_c
        "tram_d" -> return R.drawable.tram_d
        "tram_e" -> return R.drawable.tram_e
        "tram_f" -> return R.drawable.tram_f
        else -> {
            return R.drawable.tram
        }


    }
}