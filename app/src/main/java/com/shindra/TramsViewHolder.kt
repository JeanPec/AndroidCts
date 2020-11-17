package com.shindra

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController.getContext

class TramsViewHolder(GenericCardView: View) : RecyclerView.ViewHolder(GenericCardView){
    val TramLineImage: ImageView = GenericCardView.findViewById(R.id.TramLineImageView);
    val ButtonHoraires: Button = GenericCardView.findViewById(R.id.BoutonHoraires);


    fun PutTramInfoIntoCardView(tram : Trams) {
        //En fonction du nom de la ligne, on doit envoyer la bonne image (A CORRIGER POUR QUE CE SOIT DYNAMIQUE SI ON RAJOTE UNE STATION
        when (tram.ligne) {
            "A" -> TramLineImage.setImageResource(R.drawable.ic_tram_a);
            "B" -> TramLineImage.setImageResource(R.drawable.ic_tram_b);
            "C" -> TramLineImage.setImageResource(R.drawable.ic_tram_c);
            "D" -> TramLineImage.setImageResource(R.drawable.ic_tram_d);
            "E" -> TramLineImage.setImageResource(R.drawable.ic_tram_e);
            "F" -> TramLineImage.setImageResource(R.drawable.ic_tram_f);
            else -> {
                print("Erreur, nom de ligne de tram non reconnue")
            }

        }
    }



}