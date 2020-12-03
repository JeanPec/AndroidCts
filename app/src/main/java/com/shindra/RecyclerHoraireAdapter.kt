package com.shindra

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop

 class RecyclerHoraireAdapter (var listeArret: ArrayList<Stop>, private var listeTram: String) : RecyclerView.Adapter<ViewHolderHoraire>() {
     override fun onBindViewHolder(holder: ViewHolderHoraire, position: Int) {

         holder.onBind(listeArret[position], listeTram)
     }
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHoraire {
         val v = LayoutInflater.from(parent.context)
         return ViewHolderHoraire(v, parent)
     }
     override fun getItemCount(): Int {
         return listeArret.size
     }

 }