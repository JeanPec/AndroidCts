package com.shindra

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line

class TramlineViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface RecyclerItemClick {
        fun onTramlineClick(l: Line?)
    }

    private var lineLogo: ImageView
    private var tramPhoto: ImageView
    private var button: Button

    fun onBind(l: Line, callback: RecyclerItemClick) {
        lineLogo.setImageResource(when(l.name) {
            LineA -> R.drawable.ic_tram_a
            LineB -> R.drawable.ic_tram_b
            LineC -> R.drawable.ic_tram_c
            LineD -> R.drawable.ic_tram_d
            LineE -> R.drawable.ic_tram_e
            LineF -> R.drawable.ic_tram_f
            else -> R.drawable.ic_tram
        })

        //register onclick callback
        button.setOnClickListener { callback.onTramlineClick(l) }
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