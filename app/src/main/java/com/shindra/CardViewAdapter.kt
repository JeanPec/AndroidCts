package com.shindra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CardViewAdapter(trams : ArrayList<Tram>,callBack : RecyclerItemClick) : RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {
    val callback : RecyclerItemClick = callBack
    val trams = trams

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        //1- Charger la vue en xml.
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.tramcardview, parent, false)
        //2- Créer ViewHolder pour controler la vue.
        val holder = CardViewHolder(rootView,parent.context)
        //3- Retourner le ViewHolder.
        return holder

    }

    override fun getItemCount(): Int {
        return trams.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        //1- Obtenir la ligne.
        //2- Envoyer les information de la ligne dans la cellule.
        //3-
        holder.onBind(trams.get(position),callback)
    }


    class CardViewHolder(itemView: View,context:Context) : RecyclerView.ViewHolder(itemView)
    {
        var context = context
        var ligne:ImageView = itemView.findViewById(R.id.ligne)
        var image:ImageView = itemView.findViewById(R.id.image)
        var scheduleButton : Button = itemView.findViewById(R.id.scheduleButton)


        //Fonction permettant de récupérer l'id d'une image en fonction de son nom.
        fun getImageId(context: Context, pictureName: String) = context.getResources().getIdentifier("drawable/$pictureName", null, context.getPackageName())

        public fun  onBind(tram: Tram,callback : RecyclerItemClick)
        {
            scheduleButton.setOnClickListener {
                    callback.onScheduleClick(tram)
            }

            ligne.setImageResource(getImageId(context,tram.line))
            image.setImageResource(getImageId(context,tram.picture))
        }
    }
}