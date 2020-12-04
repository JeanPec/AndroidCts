package com.shindra

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

class ViewHolderHoraire(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.cardview_horaire, parent, false)) {

    var nomArret: TextView = itemView.findViewById(R.id.nom_arret)
    var heurePassage: TextView = itemView.findViewById(R.id.heure_passage)
    var nomLigne: TextView = itemView.findViewById(R.id.nom_ligne)
    var texteInformation: TextView = itemView.findViewById(R.id.texte_information)


    fun onBind(arret: Stop, nomTram: String){
        nomArret.text = arret.name
        nomLigne.text = "Ligne " + nomTram
        texteInformation.text = "stop"
        heurePassage.text = "heure"
        
    }


}