package com.shindra

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class HorairesAdapter (_Context:Context,  _StopsNames: List<String>,  _DeparturesTimes: List<String>, _LineName: String) : RecyclerView.Adapter<HorairesViewHolder>() {


    val context=_Context;
    val StopsNames = _StopsNames
    val DeparturesTimes = _DeparturesTimes
    val LineName = _LineName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorairesViewHolder {
        //Charger la cardview du tram en question
        val GenericCardView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_horaires,parent,false);

        //Creer le viewholder qui va controler la cardview
        val holder = HorairesViewHolder(GenericCardView,context);

        //Retourner le holder
        return holder;
    }

    override fun getItemCount(): Int {
        return StopsNames.size;
    }

    override fun onBindViewHolder(holder: HorairesViewHolder, position: Int) {
        //Envoyer les infos des arrets vers la cardview
        holder.PutTimeInfoIntoCardView(StopsNames[position],DeparturesTimes[position],LineName)
    }

}