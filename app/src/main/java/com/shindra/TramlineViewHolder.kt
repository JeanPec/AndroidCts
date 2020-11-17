package com.shindra

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

// stores and recycles views as they are scrolled off screen
class TramlineViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface RecyclerItemClick {
        fun OnTramlineClick(l: Line?)
    }

    internal interface LineNames {
        companion object {
            const val LineA = "Parc des Sports - Illkirch Graffenstaden"
            const val LineB = "Lingolsheim Tiergaertel - Hoenheim Gare"
            const val LineC = "Gare Centrale - Neuhof Rodolphe Reuss"
            const val LineD = "Poteries - Port du Rhin / Kehl Rathaus"
            const val LineE = "Robertsau l'Escale - Campus d'Illkirch"
            const val LineF = "Comtes - Place d'Islande"
        }
    }

    var lineLogo: ImageView
    var tramPhoto: ImageView
    var button: Button
    fun onBind(l: Line, callback: RecyclerItemClick) {
        val s = l.name
        when (s) {
            LineNames.LineA ->                 //LineA
                lineLogo.setImageResource(R.drawable.ic_tram_a)
            LineNames.LineB ->                 //LineB
                lineLogo.setImageResource(R.drawable.ic_tram_b)
            LineNames.LineC ->                 //LineC
                lineLogo.setImageResource(R.drawable.ic_tram_c)
            LineNames.LineD ->                 //LineD
                lineLogo.setImageResource(R.drawable.ic_tram_d)
            LineNames.LineE ->                 //LineE
                lineLogo.setImageResource(R.drawable.ic_tram_e)
            LineNames.LineF ->                 //LineF
                lineLogo.setImageResource(R.drawable.ic_tram_f)
        }

        //register onclick callback
        button.setOnClickListener { callback.OnTramlineClick(l) }
    }

    companion object {
        private const val TAG = "tramlineViewHolder"
    }

    init {
        Log.i(TAG, "constructor")
        lineLogo = itemView.findViewById(R.id.cvTramline_tramlogo)
        tramPhoto = itemView.findViewById(R.id.cvTramline_tramphoto)
        button = itemView.findViewById(R.id.cvTramline_button)
    }
}