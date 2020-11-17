package com.shindra

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class HorairesViewHolder (GenericCardView: View, _Context:Context) : RecyclerView.ViewHolder(GenericCardView){

val Stop_TextView: TextView = GenericCardView.findViewById(R.id.textView_Stop);
val Line_TextView: TextView = GenericCardView.findViewById(R.id.textView_Line);
val Time_TextView: TextView = GenericCardView.findViewById(R.id.textView_Time);
val context = _Context;

    fun PutTimeInfoIntoCardView(horaire : Horaires) {

        Stop_TextView.setText(horaire.stop)
        Time_TextView.setText(horaire.time)
        Line_TextView.setText("Ligne " + horaire.line)

        //En fonction du nom de la ligne, on doit afficher le texte de la bonne couleur
        when (horaire.line) {
            "A" -> Line_TextView.setTextColor(ContextCompat.getColor(context,R.color.LigneA));
            "B" -> Line_TextView.setTextColor(ContextCompat.getColor(context,R.color.LigneB));
            "C" -> Line_TextView.setTextColor(ContextCompat.getColor(context,R.color.LigneC));
            "D" -> Line_TextView.setTextColor(ContextCompat.getColor(context,R.color.LigneD));
            "E" -> Line_TextView.setTextColor(ContextCompat.getColor(context,R.color.LigneE));
            "F" -> Line_TextView.setTextColor(ContextCompat.getColor(context,R.color.LigneF));
            else -> {
                print("Erreur, nom de ligne de tram non reconnue")
            }

        }
    }



}