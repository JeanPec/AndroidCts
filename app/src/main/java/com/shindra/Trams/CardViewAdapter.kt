package com.shindra.Trams

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.RecyclerItemClick
import com.shindra.ctslibrary.bo.Line

class CardViewAdapter(trams: ArrayList<Line>?, callBack: RecyclerItemClick) : RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {
    val callback : RecyclerItemClick = callBack
    var trams : ArrayList<Line>? = trams

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        //1- Charger la vue en xml.
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.tramcardview, parent, false)
        //2- Créer ViewHolder pour controler la vue.
        val holder = CardViewHolder(rootView, parent.context)
        //3- Retourner le ViewHolder.
        return holder

    }

    override fun getItemCount(): Int {
        return trams!!.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        //1- Obtenir la ligne.
        //2- Envoyer les information de la ligne dans la cellule
        if (trams != null) {
            holder.onBind(trams!!.get(position),callback)
        }
    }
    fun setTram(lines: ArrayList<Line>?)
    {
        trams = lines
    }


    class CardViewHolder(itemView: View,context:Context) : RecyclerView.ViewHolder(itemView)
    {
        var context = context
        var ligne:ImageView = itemView.findViewById(R.id.ligne)
        var image:ImageView = itemView.findViewById(R.id.image)
        var scheduleButton : Button = itemView.findViewById(R.id.scheduleButton)

         fun  onBind(tram: Line,callback : RecyclerItemClick)
        {
            scheduleButton.setOnClickListener {
                    callback.onScheduleClick(tram)
            }

            when(tram.name)
            {
                "A"-> ligne.setImageResource(R.drawable.tram_a)
                "B"-> ligne.setImageResource(R.drawable.tram_b)
                "C"-> ligne.setImageResource(R.drawable.tram_c)
                "D"-> ligne.setImageResource(R.drawable.tram_d)
                "E"-> ligne.setImageResource(R.drawable.tram_e)
                "F"-> ligne.setImageResource(R.drawable.tram_f)
                else-> { ligne.setImageResource(R.drawable.tram)}
            }
            image.setImageResource(R.drawable.nouveau_tram_strasbourg)
        }

    }
}