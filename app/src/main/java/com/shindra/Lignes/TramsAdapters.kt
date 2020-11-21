package com.shindra.Lignes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.Horaires.HorairesActivity

class TramsAdapters(_Context:Context, _TramLinesNames: List<String>): RecyclerView.Adapter<TramsViewHolder>() {

    val context=_Context
    val tramLinesNames = _TramLinesNames

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramsViewHolder {
        //Charger la cardview du tram en question
        val GenericCardView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,parent,false);

        //Creer le viewholder qui va controler la cardview
        val TramsHolder = TramsViewHolder(GenericCardView);

        //Retourner le holder
        return TramsHolder;
    }

    override fun getItemCount(): Int {
        return tramLinesNames.size;
    }

    override fun onBindViewHolder(holder: TramsViewHolder, position: Int) {
        //Obtenir le tram en fonction de la position
        val TramLine = tramLinesNames[position];

        //Envoyer les infos du tram vers la cardview
        holder.PutTramInfoIntoCardView(TramLine);

        holder.ButtonHoraires.setOnClickListener{
            val intent = Intent(context, HorairesActivity::class.java)
            intent.putExtra("Ligne",TramLine);
            context.startActivity(intent)
        }
    }



}