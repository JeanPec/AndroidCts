package com.shindra

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class RecyclerTramAdapter(private val listetram: ArrayList<Line>) : RecyclerView.Adapter<RecyclerTramAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tram: Line = listetram[position]
       // holder.viewTram.setImageResource(tram.nom_tram)

        when (tram.name) {
            "A" -> holder.viewTram.setImageResource(R.drawable.ic_tram_a)
            "B" -> holder.viewTram.setImageResource(R.drawable.ic_tram_b)
            "C" -> holder.viewTram.setImageResource(R.drawable.ic_tram_c)
            "D" -> holder.viewTram.setImageResource(R.drawable.ic_tram_d)
            "E" -> holder.viewTram.setImageResource(R.drawable.ic_tram_e)
            "F" -> holder.viewTram.setImageResource(R.drawable.ic_tram_f)
        }



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tram, parent, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return listetram.size
    }
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var viewTram = itemView.findViewById<ImageView>(R.id.tram)
        var BoutonHoraire = itemView.findViewById<Button>(R.id.bouton_horaire)

    }
}