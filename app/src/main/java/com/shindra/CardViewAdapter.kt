package com.shindra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardViewAdapter : RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {
val _tramList = arrayOf(Tram("Ligne A",""),
    Tram("Ligne B","tram_b"),
    Tram("Ligne C","tram_c"),
    Tram("Ligne D","tram_d"),
    Tram("Ligne E","tram_e"),
    Tram("Ligne F","tram_f")
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        //1- Charger la vue en xml.
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.tramcardview,parent,false)
        //2- Créer ViewHolder pour controler la vue.
        val holder = CardViewHolder(rootView)
        //3- Retourner le ViewHolder.
        return holder

    }

    override fun getItemCount(): Int {
        return _tramList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        //1- Obtenir la ligne.
        //2- Envoyer les information de la ligne dans la cellule.
        //3-
        holder.onBind(_tramList.get(position))
    }


    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var ligne:ImageView = itemView.findViewById(R.id.ligne)
        var image:ImageView = itemView.findViewById(R.id.image)

        public fun  onBind(tram:Tram)
        {
            ligne.setImageResource(R.drawable.tram_a)
            image.setImageResource(R.drawable.nouveau_tram_strasbourg)
        }
    }
}