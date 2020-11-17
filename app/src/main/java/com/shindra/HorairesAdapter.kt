package com.shindra

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HorairesAdapter (_Context:Context) : RecyclerView.Adapter<HorairesViewHolder>() {

    val HorairesList = arrayOf(Horaires(line = "A",stop="Test1",time = "14h45"),
            Horaires(line = "A",stop="Test2",time = "17h45"),
            Horaires(line = "A",stop="Test3",time = "18h49"),
            Horaires(line = "A",stop="Test4",time = "22h51"),
            Horaires(line = "A",stop="Test5",time = "10h29"),
            Horaires(line = "A",stop="Test6",time = "07h04"),
            Horaires(line = "B",stop="sfvddsfv",time = "07h78"),
            Horaires(line = "C",stop="gdsfgsfvddsfv",time = "07h45"),
            Horaires(line = "F",stop="fdfdf",time = "07h74"),
            Horaires(line = "Z",stop="Test6",time = "07h04"),
            Horaires(line = "B",stop="sfvddsfv",time = "07h78"),
            Horaires(line = "C",stop="gdsfgsfvddsfv",time = "07h45"))

    val context=_Context;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorairesViewHolder {
        //Charger la cardview du tram en question
        val GenericCardView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_horaires,parent,false);

        //Creer le viewholder qui va controler la cardview
        val holder = HorairesViewHolder(GenericCardView,context);

        //Retourner le holder
        return holder;
    }

    override fun getItemCount(): Int {
        return HorairesList.size;
    }

    override fun onBindViewHolder(holder: HorairesViewHolder, position: Int) {
        val _Horaire = HorairesList[position];

        //Envoyer les infos du tram vers la cardview
        holder.PutTimeInfoIntoCardView(_Horaire)
    }

}