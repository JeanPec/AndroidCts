package com.shindra

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class ViewHolderTram(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var viewTram: ImageView = itemView.findViewById(R.id.tram)
    var BoutonHoraire = itemView.findViewById<Button>(R.id.bouton_horaire)


    fun onBind(tram : Line){

        viewTram.setImageResource( when (tram.name) {
            "A" -> R.drawable.ic_tram_a
            "B" -> R.drawable.ic_tram_b
            "C" -> R.drawable.ic_tram_c
            "D" -> R.drawable.ic_tram_d
            "E" -> R.drawable.ic_tram_e
            "F" -> R.drawable.ic_tram_f
            else -> {
                R.drawable.ic_tram
            }
        })

    }

}