package com.shindra

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY

class TramsAdapters(_Context:Context): RecyclerView.Adapter<TramsViewHolder>() {

    val TramsList = arrayOf(Trams(ligne = "A"),
                            Trams(ligne = "B"),
                            Trams(ligne = "C"),
                            Trams(ligne = "D"),
                            Trams(ligne = "E"),
                            Trams(ligne = "F"))
    val context=_Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramsViewHolder {
        //Charger la cardview du tram en question
        val GenericCardView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,parent,false);

        //Creer le viewholder qui va controler la cardview
        val TramsHolder = TramsViewHolder(GenericCardView);

        //Retourner le holder
        return TramsHolder;
    }

    override fun getItemCount(): Int {
        return TramsList.size;
    }

    override fun onBindViewHolder(holder: TramsViewHolder, position: Int) {
        //Obtenir le tram en fonction de la position
        val Tram = TramsList[position];

        //Envoyer les infos du tram vers la cardview
        holder.PutTramInfoIntoCardView(Tram);

        holder.ButtonHoraires.setOnClickListener{
            val intent = Intent(context, horaires_activity::class.java)
            intent.putExtra("Ligne", Tram.ligne);
            context.startActivity(intent)
        }
    }


}