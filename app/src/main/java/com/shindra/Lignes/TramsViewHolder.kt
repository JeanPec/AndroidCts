package com.shindra.Lignes

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R

class TramsViewHolder(GenericCardView: View) : RecyclerView.ViewHolder(GenericCardView){
    val TramLineImage: ImageView = GenericCardView.findViewById(R.id.TramLineImageView);
    val ButtonHoraires: Button = GenericCardView.findViewById(R.id.BoutonHoraires);

    fun PutTramInfoIntoCardView(TramLineLetter : String) {
        TramLineImage.setImageResource(when(TramLineLetter){
            "A" -> R.drawable.ic_tram_a
            "B" -> R.drawable.ic_tram_b
            "C" -> R.drawable.ic_tram_c
            "D" -> R.drawable.ic_tram_d
            "E" -> R.drawable.ic_tram_e
            "F" -> R.drawable.ic_tram_f
            else -> {
                R.drawable.ic_tram;
            }
        })
        }
    }