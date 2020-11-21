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


class RecyclerTramAdapter(private val listetram: ArrayList<Line>) : RecyclerView.Adapter<ViewHolderTram>() {
    override fun onBindViewHolder(holder: ViewHolderTram, position: Int) {
        holder.onBind(listetram[position])

       /* val tram: Line = listetram[position]
        holder.viewTram.setImageResource( when (tram.name) {
            "A" -> R.drawable.ic_tram_a
            "B" -> R.drawable.ic_tram_b
            "C" -> R.drawable.ic_tram_c
            "D" -> R.drawable.ic_tram_d
            "E" -> R.drawable.ic_tram_e
            "F" -> R.drawable.ic_tram_f
            else -> {
                R.drawable.ic_tram
            }
        })*/

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTram {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tram, parent, false)
        return ViewHolderTram(v)
    }
    override fun getItemCount(): Int {
        return listetram.size
    }
   /* class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var viewTram: ImageView = itemView.findViewById(R.id.tram)
        var BoutonHoraire = itemView.findViewById<Button>(R.id.bouton_horaire)
    }*/
}